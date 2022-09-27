package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.picpay.desafio.android.database.DatabaseUser
import com.picpay.desafio.android.database.UserDao
import com.picpay.desafio.android.database.asDomainModel
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.network.NetworkUser
import com.picpay.desafio.android.network.NetworkUserContainer
import com.picpay.desafio.android.network.PicPayService
import com.picpay.desafio.android.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class PicPayRepository @Inject constructor(
    private val userDao: UserDao,
    private val picpayService: PicPayService
) {

    val users: LiveData<List<User>> =
        Transformations.map(userDao.getUsers()) {
            it.asDomainModel()
        }

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            try {
                val users = picpayService.getUsersAsync().await()
                val usersList: List<NetworkUser> = users.map {
                    NetworkUser(
                        img = it.img,
                        name = it.name,
                        id = it.id,
                        username = it.username
                    )
                }
                val contactList = NetworkUserContainer(usersList)
                userDao.insertAll(*contactList.asDatabaseModel())
            } catch (networkError: IOException) {
                //todo offline sanckbar
            }
        }
    }

    fun getDatabaseUser(): LiveData<List<DatabaseUser>> {
        return userDao.getUsers()
    }

    suspend fun getApiResponseUser(): List<User> = withContext(Dispatchers.IO) {
        return@withContext picpayService.getUsersAsync().await()
    }
}
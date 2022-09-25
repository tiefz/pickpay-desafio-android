package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.picpay.desafio.android.database.UsersDatabase
import com.picpay.desafio.android.database.asDomanModel
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.network.Network
import com.picpay.desafio.android.network.NetworkUser
import com.picpay.desafio.android.network.NetworkUserContainer
import com.picpay.desafio.android.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class PicPayRepository(private val database: UsersDatabase) {

    val users: LiveData<List<User>> =
        Transformations.map(database.userDao.getUsers()) {
            it.asDomanModel()
        }

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            try {
                val users = Network.picpayContacts.getUsersAsync().await()
                val usersList: List<NetworkUser> = users.map {
                    NetworkUser(
                        img = it.img,
                        name = it.name,
                        id = it.id,
                        username = it.username
                    )
                }
                val contactList = NetworkUserContainer(usersList)
                database.userDao.insertAll(*contactList.asDatabaseModel())
            } catch (networkError: IOException) {
                //todo offline sanckbar
            }
        }
    }
}
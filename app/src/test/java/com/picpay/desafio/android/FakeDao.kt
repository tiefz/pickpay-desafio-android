package com.picpay.desafio.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.database.DatabaseUser
import com.picpay.desafio.android.database.UserDao

class FakeDao : UserDao {

    private val users = MutableLiveData<List<DatabaseUser>>()

    override fun getUsers(): LiveData<List<DatabaseUser>> {
        return users
    }

    override fun insertAll(vararg users: DatabaseUser) {
        this.users.value?.toMutableList()?.addAll(users)
    }
}
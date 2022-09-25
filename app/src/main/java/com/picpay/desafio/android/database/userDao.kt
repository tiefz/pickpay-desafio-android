package com.picpay.desafio.android.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("select * from databaseuser")
    fun getUsers(): LiveData<List<DatabaseUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: DatabaseUser)
}
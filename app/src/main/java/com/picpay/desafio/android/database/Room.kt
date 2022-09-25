package com.picpay.desafio.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseUser::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun getDao(): UserDao

    companion object {
        private lateinit var INSTANCE: UsersDatabase

        fun getDatabase(context: Context): UsersDatabase {
            synchronized(UsersDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UsersDatabase::class.java,
                        "users"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
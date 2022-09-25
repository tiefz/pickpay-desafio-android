package com.picpay.desafio.android.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.domain.User

@Entity
data class DatabaseUser constructor(
    val img: String,
    val name: String,
    @PrimaryKey
    val id: Int,
    val username: String
)

fun List<DatabaseUser>.asDomanModel(): List<User> {
    return map {
        User(
            img = it.img,
            name = it.name,
            id = it.id,
            username = it.username
        )
    }
}
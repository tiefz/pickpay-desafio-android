package com.picpay.desafio.android.network

import com.picpay.desafio.android.database.DatabaseUser
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUserContainer(val users: List<NetworkUser>)

@JsonClass(generateAdapter = true)
data class NetworkUser(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
)

//fun NetworkUserContainer.asDomainModel(): List<User> {
//    return users.map {
//        User(
//            img = it.img,
//            name = it.name,
//            id = it.id,
//            username = it.username)
//    }
//}

fun NetworkUserContainer.asDatabaseModel(): Array<DatabaseUser> {
    return users.map {
        DatabaseUser(
            img = it.img,
            name = it.name,
            id = it.id,
            username = it.username
        )
    }.toTypedArray()
}
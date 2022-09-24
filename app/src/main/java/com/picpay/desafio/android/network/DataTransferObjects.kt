package com.picpay.desafio.android.network

import com.picpay.desafio.android.domain.User
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

fun NetworkUserContainer.asDomainModel(): List<User> {
    return users.map {
        User(
            img = it.img,
            name = it.name,
            id = it.id,
            username = it.username
        )
    }
}
package com.picpay.desafio.android.network

import com.picpay.desafio.android.domain.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    fun getUsersAsync(): Deferred<List<User>>
}
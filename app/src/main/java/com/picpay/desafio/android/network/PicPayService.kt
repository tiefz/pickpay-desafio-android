package com.picpay.desafio.android.network

import com.picpay.desafio.android.domain.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}
package com.picpay.desafio.android.di

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.picpay.desafio.android.database.UserDao
import com.picpay.desafio.android.database.UsersDatabase
import com.picpay.desafio.android.network.PicPayService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PicpayModule {

    @Singleton
    @Provides
    fun getDatabase(context: Application): UsersDatabase {
        return UsersDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun getDao(usersDatabase: UsersDatabase): UserDao {
        return usersDatabase.getDao()
    }

    private const val baseUrl = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun getPicpayService(retrofit: Retrofit): PicPayService {
        return retrofit.create(PicPayService::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}
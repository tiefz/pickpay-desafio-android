package com.picpay.desafio.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.database.getDatabase
import com.picpay.desafio.android.repository.PicPayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val usersRepository = PicPayRepository(database)

    init {
        viewModelScope.launch {
            usersRepository.refreshUsers()
        }
    }

    val contactList = usersRepository.users
}
package com.picpay.desafio.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.database.getDatabase
import com.picpay.desafio.android.repository.PicPayRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val usersRepository = PicPayRepository(database)

//    private val _hasNetworkData = MutableLiveData<Boolean>()
//    val hasNetworkData: LiveData<Boolean>
//        get() = _hasNetworkData

    init {
        viewModelScope.launch {
            usersRepository.refreshUsers()
        }
    }

    val contactList = usersRepository.users

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
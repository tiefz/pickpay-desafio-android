package com.picpay.desafio.android.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.network.Network
import com.picpay.desafio.android.network.asDomainModel
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _contactList = MutableLiveData<List<User>>()
    val contactList: LiveData<List<User>>
        get() = _contactList

    init {
        getContactList()
    }

    private fun getContactList() = viewModelScope.launch {
        try {
            val contactList = Network.picpayContacts.getUsers().await()
            _contactList.postValue(contactList.asDomainModel())
        } catch (networkError: IOException) {

        }
    }


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
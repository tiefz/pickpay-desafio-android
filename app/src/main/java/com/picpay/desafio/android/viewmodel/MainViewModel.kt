package com.picpay.desafio.android.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.network.Network
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _contactList = MutableLiveData<List<User>>()
    val contactList: LiveData<List<User>>
        get() = _contactList

    private val _hasNetworkData = MutableLiveData<Boolean>()
    val hasNetworkData: LiveData<Boolean>
        get() = _hasNetworkData

    init {
        getContactList()
    }

    private fun getContactList() = viewModelScope.launch {
        try {
            val contactList = Network.picpayContacts.getUsers().await()
            _contactList.postValue(contactList)
            _hasNetworkData.value = true
        } catch (networkError: IOException) {
            _hasNetworkData.value = false
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
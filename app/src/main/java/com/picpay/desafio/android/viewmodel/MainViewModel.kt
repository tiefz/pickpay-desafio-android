package com.picpay.desafio.android.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.repository.PicPayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(picPayRepository: PicPayRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            picPayRepository.refreshUsers()
        }
    }

    val progressVisibility: ObservableField<Int> = ObservableField(View.VISIBLE)
    val contactList = picPayRepository.users

    fun updateView() {
        progressVisibility.set(View.GONE)
    }
}
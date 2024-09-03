package com.example.lvl4example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lvl4example.data.api.util.Resource
import com.example.lvl4example.data.model.RandomNumber
import com.example.lvl4example.repository.NumbersRepository
import kotlinx.coroutines.launch

class NumbersViewModel(application: Application) : AndroidViewModel(application) {

    private val _numbersRepository = NumbersRepository()

    val numberResource: LiveData<Resource<RandomNumber>>
        get() = _numberResource

    private val _numberResource: MutableLiveData<Resource<RandomNumber>> =
        MutableLiveData(Resource.Empty())

    fun getNumber() {
        _numberResource.value = Resource.Loading()

        viewModelScope.launch {
            _numberResource.value = _numbersRepository.getRandomNumber()
        }
    }
}
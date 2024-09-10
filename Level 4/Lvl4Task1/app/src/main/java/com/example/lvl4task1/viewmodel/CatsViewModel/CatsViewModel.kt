package com.example.lvl4task1.viewmodel.CatsViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lvl4task1.data.api.util.Resource
import com.example.lvl4task1.data.model.Cat
import com.example.lvl4task1.repository.CatsRepository
import kotlinx.coroutines.launch

class CatsViewModel(application: Application): AndroidViewModel(application) {

    private val _catsRepository: CatsRepository = CatsRepository()

    val catResource: LiveData<Resource<Cat>>
        get() = _catResource

    private val _catResource: MutableLiveData<Resource<Cat>> =
        MutableLiveData(Resource.Empty())

    fun getCat() {
        _catResource.value = Resource.Loading()

        viewModelScope.launch {
            _catResource.value = _catsRepository.getCat()
        }
    }
}
package com.example.hugginfaceapi


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hugginfaceapi.network.Api
import com.example.hugginfaceapi.network.ApiService
import com.example.hugginfaceapi.network.Disease
import com.example.hugginfaceapi.network.RequestInp
import com.example.hugginfaceapi.network.retrofit
import kotlinx.coroutines.launch

class SymptomViewModel : ViewModel() {

    private val _diseases = MutableLiveData<List<List<Disease>>>()
    val diseases get() = _diseases

    fun getDiseasePrediction(inputs: String) {
        val requestBody = RequestInp(inputs)

        viewModelScope.launch {
            val response = Api.retrofitService.getDiseasePrediction(requestBody)
            _diseases.value = response
            Log.d("SymptomViewModel", "getDiseasePrediction: ${diseases.value?.get(0)}")
        }
    }
}


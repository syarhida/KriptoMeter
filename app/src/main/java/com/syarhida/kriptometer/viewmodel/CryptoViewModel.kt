package com.syarhida.kriptometer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syarhida.kriptometer.model.Crypto
import com.syarhida.kriptometer.network.RetrofitClient
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val _cryptoList = MutableLiveData<List<Crypto>>()
    val cryptoList: LiveData<List<Crypto>> = _cryptoList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchCryptoData() {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getCryptoData()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _cryptoList.value = it.data
                    } ?: run {
                        _errorMessage.value = "Tidak ada data tersedia"
                    }
                } else {
                    _errorMessage.value = "Gagal memuat data. Coba lagi."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Gagal memuat data. Coba lagi."
            } finally {
                _isLoading.value = false
            }
        }
    }
}


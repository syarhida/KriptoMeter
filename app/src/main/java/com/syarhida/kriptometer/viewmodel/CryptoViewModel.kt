package com.syarhida.kriptometer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syarhida.kriptometer.adapter.LoadMoreItem
import com.syarhida.kriptometer.model.Crypto
import com.syarhida.kriptometer.network.RetrofitClient
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val _displayList = MutableLiveData<List<Any>>()
    val displayList: LiveData<List<Any>> = _displayList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private var allCryptoData: List<Crypto> = emptyList()
    private var currentDisplayCount = 0
    private val PAGE_SIZE = 20
    private val MAX_ITEMS = 100
    
    private fun resetState() {
        allCryptoData = emptyList()
        currentDisplayCount = 0
        _displayList.value = emptyList()
    }

    fun fetchCryptoData() {
        _isLoading.value = true
        _errorMessage.value = null
        
        // Reset state first to prevent bugs
        resetState()

        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getCryptoData()
                if (response.isSuccessful) {
                    response.body()?.let {
                        // Ambil max 100 item dari API
                        allCryptoData = it.data.take(MAX_ITEMS)
                        currentDisplayCount = 0
                        
                        // Load first page
                        loadMoreData()
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
    
    fun loadMoreData() {
        if (allCryptoData.isEmpty()) return
        
        val startIndex = currentDisplayCount
        val endIndex = minOf(startIndex + PAGE_SIZE, allCryptoData.size)
        
        if (startIndex < endIndex) {
            // Get next batch from allCryptoData based on currentDisplayCount
            val displayedCryptos = allCryptoData.subList(0, endIndex)
            currentDisplayCount = endIndex
            
            // Create new display list
            val newDisplayList = mutableListOf<Any>()
            newDisplayList.addAll(displayedCryptos)
            
            // Add load more button only if there's more data
            if (currentDisplayCount < allCryptoData.size) {
                newDisplayList.add(LoadMoreItem())
            }
            
            _displayList.value = newDisplayList
        }
    }
}


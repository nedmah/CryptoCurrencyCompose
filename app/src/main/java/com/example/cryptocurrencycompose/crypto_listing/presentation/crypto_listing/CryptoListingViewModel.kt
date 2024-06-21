package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencycompose.commons.util.Resource
import com.example.cryptocurrencycompose.crypto_listing.domain.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListingViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    var state by mutableStateOf(CryptoListingsState())

    var searchJob : Job? = null

    init {
        getCryptoListings()
    }

    fun onEvent(event : CryptoListingsEvents){
        when(event){
            is CryptoListingsEvents.OnSearchQueryChange -> {
                state = state.copy(
                    searchQuery = event.query
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCryptoListings()
                }
            }
            is CryptoListingsEvents.Refresh -> {
                getCryptoListings(fetchFromNetwork = true)
            }
        }
    }

     private fun getCryptoListings(
         query : String = state.searchQuery,
         fetchFromNetwork : Boolean = false
         ){
            viewModelScope.launch {
                repository.getListings(fetchFromNetwork, query).collect{result ->
                    when(result){
                        is Resource.Error -> {
                            state = state.copy(
                                error = "An error has occured"
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            state = state.copy(
                                isLoading = false,
                                isRefreshing = false,
                                cryptos = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }
     }

}
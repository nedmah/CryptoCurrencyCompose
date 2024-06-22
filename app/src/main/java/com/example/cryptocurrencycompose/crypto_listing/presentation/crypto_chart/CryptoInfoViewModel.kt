package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_chart

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencycompose.commons.util.Resource
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoInfoModel
import com.example.cryptocurrencycompose.crypto_listing.domain.repository.CryptoInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoInfoViewModel @Inject constructor(
    private val savedStateHande: SavedStateHandle,
    private val repository: CryptoInfoRepository
) : ViewModel() {

    var state by mutableStateOf(CryptoInfoState())

    val id = savedStateHande.get<String>("id") ?: ""

    init {
        viewModelScope.launch {
            getCryptoInfoByYear(id)
            state = state.copy(yearButtonPushed = true)
        }
    }


    fun onEvent(event: CryptoInfoEvents) {
        when (event) {
            is CryptoInfoEvents.DayButton -> getCryptoInfoByDay(id)
            is CryptoInfoEvents.WeekButton -> getCryptoInfoByWeek(id)
            is CryptoInfoEvents.MonthButton -> getCryptoInfoByMonth(id)
            is CryptoInfoEvents.YearButton -> getCryptoInfoByYear(id)
        }
    }




    private fun getCryptoInfoByDay(id: String) {
        viewModelScope.launch {
            when (val info = repository.getCryptoInfo(id, "h1")) {

                is Resource.Error -> state = state.copy(
                    error = "An error has occured"
                )

                is Resource.Loading -> TODO()

                is Resource.Success -> info.data?.let {data ->
                    val groupedList = data.takeLast(24)
                    state = state.copy(
                        cryptoInfo = groupedList,
                        monthButtonPushed = false,
                        yearButtonPushed = false,
                        weekButtonPushed = false,
                        dayButtonPushed = true
                    )
                }

            }
        }
    }


    private fun getCryptoInfoByWeek(id: String) {
        viewModelScope.launch {
            when (val info = repository.getCryptoInfo(id, "h1")) {

                is Resource.Error -> state = state.copy(
                    error = "An error has occured"
                )

                is Resource.Loading -> TODO()

                is Resource.Success -> info.data?.let {data ->
                    val groupedList = data.filter { it.time == 12 }.takeLast(7)

                    state = state.copy(
                        cryptoInfo = groupedList,
                        monthButtonPushed = false,
                        yearButtonPushed = false,
                        weekButtonPushed = true,
                        dayButtonPushed = false
                    )
                }

            }
        }
    }



    private fun getCryptoInfoByMonth(id: String) {
        viewModelScope.launch {
            when (val info = repository.getCryptoInfo(id, "h1")) {

                is Resource.Error -> state = state.copy(
                    error = "An error has occured"
                )

                is Resource.Loading -> TODO()

                is Resource.Success -> info.data?.let {data ->
                    val groupedList = data.filter { it.time == 12 }

                    state = state.copy(
                        cryptoInfo = groupedList,
                        monthButtonPushed = true,
                        yearButtonPushed = false,
                        weekButtonPushed = false,
                        dayButtonPushed = false
                    )
                }

            }
        }
    }


    private fun getCryptoInfoByYear(id: String) {
        viewModelScope.launch {
            when (val info = repository.getCryptoInfo(id, "d1")) {

                is Resource.Error -> state = state.copy(
                    error = "An error has occured"
                )

                is Resource.Loading -> TODO()

                is Resource.Success -> info.data?.let {data ->
                    val groupedList = data.groupBy { it.month to it.year }.map { (monthYear, daysInMonth) ->
                        val (month, year) = monthYear
                        val firstDayOfMonth = daysInMonth.minByOrNull { it.day } ?: error("Нет дней в месяце")
                        CryptoInfoModel(
                            price = firstDayOfMonth.price,
                            year = year,
                            month = month,
                            day = firstDayOfMonth.day,
                            time = 12, // Полдень
                            date = firstDayOfMonth.date
                        )
                    }
                    state = state.copy(
                        cryptoInfo = groupedList,
                        yearButtonPushed = true,
                        weekButtonPushed = false,
                        monthButtonPushed = false,
                        dayButtonPushed = false
                    )
                }

            }
        }
    }

}
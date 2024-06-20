package com.example.cryptocurrencycompose.crypto_listing.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CryptoListingsScreen(
    viewModel: CryptoListingViewModel = hiltViewModel(),
    modifier: Modifier
) {
    val state = viewModel.state

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { viewModel.onEvent(CryptoListingsEvents.OnSearchQueryChange(it)) },
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = { "Поиск" },
            maxLines = 1,
            singleLine = true
        )
        Spacer(modifier = modifier.size(10.dp))
        LazyColumn(
            modifier = modifier.fillMaxSize(),

            ) {
            items(state.cryptos.size) { i ->

                ListingsItem(modifier = modifier.fillMaxWidth(), crypto = state.cryptos[i])

                if (i < state.cryptos.size) {
                    HorizontalDivider(modifier = modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }

}
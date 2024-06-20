package com.example.cryptocurrencycompose.crypto_listing.presentation

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun CryptoListingsScreen(
    navigator: DestinationsNavigator,
    viewModel: CryptoListingViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.state

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

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

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(CryptoListingsEvents.Refresh) }
        ) {
            LazyColumn(
                modifier = modifier.fillMaxSize(),

                ) {
                items(state.cryptos.size) { i ->

                    ListingsItem(modifier = modifier.fillMaxWidth(), crypto = state.cryptos[i])

                    if (i < state.cryptos.size) {
                        HorizontalDivider(
                            modifier = modifier
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    //TODO: доп инфа?
                                }
                        )
                    }
                }
            }
        }
    }

}
package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_listing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencycompose.crypto_listing.presentation.destinations.CryptoChartDestination
import com.example.cryptocurrencycompose.ui.theme.BlueBackground
import com.example.cryptocurrencycompose.ui.theme.LightBlue
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
        modifier = modifier
            .fillMaxSize()
            .background(BlueBackground)
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { viewModel.onEvent(CryptoListingsEvents.OnSearchQueryChange(it)) },
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(LightBlue),
            placeholder = { "Поиск" },
            maxLines = 1,
            singleLine = true
        )
        if(state.isLoading){
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(BlueBackground)
            ) {
                items(15){
                    ShimmerListItem(modifier)
                }
            }
        }else {
            SwipeRefresh(
                modifier = modifier,
                state = swipeRefreshState,
                onRefresh = { viewModel.onEvent(CryptoListingsEvents.Refresh) }
            ) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize(),

                    ) {
                    items(state.cryptos.size) { i ->

                        ListingsItem(
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable {
                                    navigator.navigate(
                                        CryptoChartDestination(state.cryptos[i].id)
                                    )
                                },
                            crypto = state.cryptos[i]
                        )

                        if (i < state.cryptos.size) {
                            HorizontalDivider(
                                modifier = modifier
                                    .padding(horizontal = 16.dp),
                                color = LightBlue
                            )
                        }
                    }
                }
            }

        }

    }


}
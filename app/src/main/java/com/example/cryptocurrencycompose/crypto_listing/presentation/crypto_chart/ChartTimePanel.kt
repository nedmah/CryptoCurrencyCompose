package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_chart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_listing.CryptoListingViewModel


@Composable
fun ChartTimeRow(
    viewModel: CryptoInfoViewModel,
) {
    var selectedButton by remember { mutableStateOf("Year") }

    Row(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextButton(
            text = "Year",
            isSelected = selectedButton == "Year",
            onClick = {
                selectedButton = "Year"
                viewModel.onEvent(CryptoInfoEvents.YearButton)
            }
        )
        TextButton(
            text = "Month",
            isSelected = selectedButton == "Month",
            onClick = {
                selectedButton = "Month"
                viewModel.onEvent(CryptoInfoEvents.MonthButton)
            }
        )
        TextButton(
            text = "Week",
            isSelected = selectedButton == "Week",
            onClick = {
                selectedButton = "Week"
                viewModel.onEvent(CryptoInfoEvents.WeekButton)
            }
        )
        TextButton(
            text = "Day",
            isSelected = selectedButton == "Day",
            onClick = {
                selectedButton = "Day"
                viewModel.onEvent(CryptoInfoEvents.DayButton)
            }
        )
    }
}




@Composable
fun TextButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color = if (isSelected) Color.Yellow else Color.White)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Text(text = text, color = Color.Black)
    }
}
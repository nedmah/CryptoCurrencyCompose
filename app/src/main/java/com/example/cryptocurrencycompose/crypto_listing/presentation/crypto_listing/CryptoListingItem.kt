package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_listing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptocurrencycompose.commons.util.shimmerEffect
import com.example.cryptocurrencycompose.crypto_listing.data.util.CryptoInfoFormatter
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoModel
import com.example.cryptocurrencycompose.ui.theme.GreenPercent
import com.example.cryptocurrencycompose.ui.theme.RedPercent
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun ListingsItem(
    modifier: Modifier = Modifier,
    crypto: CryptoModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(modifier = Modifier.weight(1f)) {
                Text(text = crypto.name)
            }

            Box(modifier = Modifier.weight(1f)) {
                Text(text = crypto.symbol)
            }

            Box(modifier = Modifier.weight(1f)) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    val percentage = crypto.percentage.toDoubleOrNull() ?: 0.0
                    val percentageColor = if (percentage > 0) GreenPercent else RedPercent
                    val percentageText = formatPercentage(percentage)


                    Text(
                        text = CryptoInfoFormatter().formatPrice(crypto.price),
                    )

                    Text(
                        text = percentageText,
                        color = percentageColor,
                        fontSize = 12.sp
                    )

                }
            }
        }
    }

}



private fun formatPercentage(percentage: Double): String {
    return if (percentage > 0) {
        "+%.3f".format(percentage)
    } else {
        "%.3f".format(percentage)
    }
}


@Composable
fun ShimmerListItem(
    modifier: Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
            .padding(12.dp),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .height(18.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )
            Spacer(modifier = modifier.width(60.dp))
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(50.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerEffect()
            )
            Spacer(modifier = modifier.width(60.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerEffect()
                )
            }
        }
    }
}
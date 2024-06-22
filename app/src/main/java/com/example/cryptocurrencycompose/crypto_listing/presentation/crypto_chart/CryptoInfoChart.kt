package com.example.cryptocurrencycompose.crypto_listing.presentation.crypto_chart

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.ramcosta.composedestinations.annotation.Destination


@Composable
@Destination
fun CryptoChart(
    id: String,
    modifier: Modifier = Modifier,
    viewModel: CryptoInfoViewModel = hiltViewModel(),
) {
    val state = viewModel.state

//    val pointsData: List<Point> =
//        listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))

    if (state.cryptoInfo.isNotEmpty()) {

        Log.d("TESTINGS", state.cryptoInfo.toString())
        val pointsData: List<Point> = state.cryptoInfo.mapIndexed() { index, data ->
            Point(
                x = index.toFloat(),
                y = data.price.toFloat()
            )
        }
        val priceLabels = pointsData.sortedBy { it.y }

        val steps = pointsData.size-1

        val xAxisData = AxisData.Builder()
            .axisStepSize(100.dp)
            .backgroundColor(Color.White)
            .steps(pointsData.size - 1)
            .labelData { i -> i.inc().toString() }
            .labelAndAxisLinePadding(15.dp)
            .build()

        val yAxisData = AxisData.Builder()
            .steps(steps)
            .backgroundColor(Color.White)
            .labelAndAxisLinePadding(20.dp)
            .labelData { i ->
                priceLabels[i].y.toString()
            }.build()


        val lineChartData = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = pointsData,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(),
                        ShadowUnderLine(),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisData,
            gridLines = GridLines(),
            backgroundColor = Color.White
        )



        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = id,
                modifier = modifier.padding(25.dp),
                fontSize = 34.sp,
                color = Color.Black
            )

            Spacer(modifier = modifier.size(60.dp))

            ChartTimeRow(viewModel = viewModel)

            LineChart(
                modifier = modifier,
                lineChartData = lineChartData
            )

        }
    }
}
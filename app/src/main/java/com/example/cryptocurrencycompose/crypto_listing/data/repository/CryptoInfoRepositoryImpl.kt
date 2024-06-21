package com.example.cryptocurrencycompose.crypto_listing.data.repository

import com.example.cryptocurrencycompose.commons.util.Resource
import com.example.cryptocurrencycompose.crypto_listing.data.remote.CryptoApi
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoInfoModel
import com.example.cryptocurrencycompose.crypto_listing.domain.repository.CryptoInfoRepository
import javax.inject.Inject

class CryptoInfoRepositoryImpl @Inject constructor(
    private val api : CryptoApi
) : CryptoInfoRepository {

    override suspend fun getCryptoInfo(id: String, interval: String): Resource<List<CryptoInfoModel>> {

        return try {
            //TODO: группируем листы в зависимости от интервала, мапим
            Resource.Success()
        }catch (e : Exception){
            Resource.Error(message = e.message ?: "Unknown error has occurred")
        }
    }


//    private fun aggregateHourlyData(assetPrices: List<AssetPrice>): List<AssetPrice> {
//        val groupedData = assetPrices.groupBy { getHourFromTimestamp(it.time) }
//        return groupedData.map { (hour, prices) ->
//            val averagePrice = prices.map { it.priceUsd.toDouble() }.average().toString()
//            val latestPrice = prices.last()
//            AssetPrice(
//                priceUsd = averagePrice,
//                time = latestPrice.time,
//                circulatingSupply = latestPrice.circulatingSupply,
//                date = latestPrice.date
//            )
//        }
//    }

}
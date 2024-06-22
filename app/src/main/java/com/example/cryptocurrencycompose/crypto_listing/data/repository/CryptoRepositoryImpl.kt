package com.example.cryptocurrencycompose.crypto_listing.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cryptocurrencycompose.commons.util.Resource
import com.example.cryptocurrencycompose.crypto_listing.data.local.CryptoDb
import com.example.cryptocurrencycompose.crypto_listing.data.mapper.toCryptoListingsEntity
import com.example.cryptocurrencycompose.crypto_listing.data.mapper.toCryptoModel
import com.example.cryptocurrencycompose.crypto_listing.data.remote.CryptoApi
import com.example.cryptocurrencycompose.crypto_listing.domain.model.CryptoModel
import com.example.cryptocurrencycompose.crypto_listing.domain.repository.CryptoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoRepositoryImpl @Inject constructor(
    private val api : CryptoApi,
    private val db : CryptoDb
): CryptoRepository {

    private val dao = db.getCryptoListingDao()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getListings(
        fetchFromNetwork: Boolean,
        query: String
    ): Flow<Resource<List<CryptoModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchForListings(query)
            emit(Resource.Success(
                localListings.map { it.toCryptoModel() }
            ))

            val dbIsEmpty = query.isBlank() && localListings.isEmpty()
            val shouldLoadFromCache = !dbIsEmpty && !fetchFromNetwork
            if(shouldLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                api.getCryptoListings()
            }catch (e : HttpException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't retrieve data"))
                null
            }catch (e : IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't retrieve data"))
                null
            }

            remoteListings?.let{
                val listings = it.data.map {item -> item.toCryptoModel() }
                dao.clearListings()
                dao.insertListings(listings.map {item -> item.toCryptoListingsEntity() })

                emit(Resource.Success(
                    data = dao
                        .searchForListings("")
                        .map { listingItem -> listingItem.toCryptoModel() }))
                emit(Resource.Loading(false))
            }
        }
    }

}
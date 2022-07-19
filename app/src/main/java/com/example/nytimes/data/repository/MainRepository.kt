package com.example.nytimes.data.repository

import com.example.nytimes.data.network.api.NetworkHelper
import com.example.nytimes.data.network.api.ResultWrapper
import com.example.nytimes.data.network.api.NYTimesApi
import com.example.nytimes.data.responses.MostViewedResponse
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val NYTimesApi: NYTimesApi,
    private val ioDispatcher: CoroutineDispatcher) : NetworkHelper<MainRepository.Params, Response<MostViewedResponse>>(ioDispatcher) {

    data class Params constructor(
        val mSection: String,
        val mPeriod: String,
        val mApiKey: String

    ) {
        companion object {
            fun create(
                mSection: String,
                mPeriod: String,
                mApiKey: String
            ) = Params(mSection, mPeriod, mApiKey)
        }
    }

    override suspend fun buildUseCase(parameters: Params?): ResultWrapper<Response<MostViewedResponse>> {
        return with(parameters!!)
        {
            ResultWrapper.Success(
                NYTimesApi.getMostViewed(
                    mSection = mSection,
                    mPeriod = mPeriod,
                    mApiKey = mApiKey
                )
            )
        }
    }


}
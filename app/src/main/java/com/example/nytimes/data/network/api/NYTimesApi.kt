package com.example.nytimes.data.network.api

import com.example.nytimes.data.responses.MostViewedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTimesApi {

    @GET("mostviewed/{section}/{period}.json")
    suspend fun getMostViewed(
        @Path("section") mSection: String?,
        @Path("period") mPeriod: String?,
        @Query("api-key") mApiKey: String?):Response<MostViewedResponse>
}
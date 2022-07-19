package com.example.nytimes.data.network

import com.example.nytimes.data.network.api.NYTimesApi
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class MostViewArticleListTest {
    private lateinit var service: NYTimesApi
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NYTimesApi::class.java)
    }
    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            server.enqueue(mockResponse)
        }
    }
    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getNYTimes_sentRequest_receivedExpected() {
        runBlocking {

            enqueueMockResponse("Results.json")
            val responseBody = service.getMostViewed("all-sections", "7","wjNAtiQyHfCLKiv69HMc1HDd3qmCrDbG").body()
            assertTrue(responseBody!!.results?.isNotEmpty())
            val request = server.takeRequest()
            assertTrue(request.path.equals("/mostviewed/all-sections/7.json?api-key=wjNAtiQyHfCLKiv69HMc1HDd3qmCrDbG"))
        }
    }
    @Test
    fun getNYArticles_size_check() {
        runBlocking {
            enqueueMockResponse("Results.json")
            val responseBody = service.getMostViewed("all-sections", "7","wjNAtiQyHfCLKiv69HMc1HDd3qmCrDbG").body()
            assertTrue(responseBody!!.results?.size > 0)
        }
    }
}
package com.example.nytimes.data.network.api

class NetworkHelperTest {

   /* private val dispatcher = TestCoroutineDispatcher()

    lateinit  var  networkHelper: NetworkHelper


    @Test
    fun `when lambda returns successfully then it should emit the result as success`() {
        runBlocking {
            val lambdaResult = true
            val result = MainRepository.(dispatcher) { lambdaResult }
            Assert.assertEquals(ResultWrapper.Success(lambdaResult), result)
        }
    }

    @Test
    fun `when lambda throws IOException then it should emit the result as NetworkError`() {
        runBlockingTest {
            val result = safeApiCall(dispatcher) { throw IOException() }
            Assert.assertEquals(ResultWrapper.NetworkError, result)
        }
    }

    @Test
    fun `when lambda throws HttpException then it should emit the result as GenericError`() {
        val errorBody = "{\"errors\": [\"Unexpected parameter\"]}".toResponseBody("application/json".toMediaTypeOrNull())

        runBlockingTest {
            val result = safeApiCall(dispatcher) {
                throw HttpException(Response.error<Any>(422, errorBody))
            }
            Assert.assertEquals(
                ResultWrapper.GenericError(
                    422,
                    ErrorResponse(listOf("Unexpected parameter"))
                ), result
            )
        }
    }

    @Test
    fun `when lambda throws unknown exception then it should emit GenericError`() {
        runBlockingTest {
            val result = execute(dispatcher) {
                throw IllegalStateException()
            }
            Assert.assertEquals(ResultWrapper.GenericError(), result)
        }
    }

    public override fun setUp() {
         networkHelper = NetworkHelper<MainRepository.Params, retrofit2.Response<MostViewedResponse>>(dispatcher)
    }*/
}
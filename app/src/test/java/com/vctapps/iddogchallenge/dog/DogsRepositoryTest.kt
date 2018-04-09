package com.vctapps.iddogchallenge.dog

import com.squareup.okhttp.mockwebserver.MockResponse
import com.vctapps.iddogchallenge.BaseNetworkTest
import com.vctapps.iddogchallenge.dog.data.DogsRepository
import com.vctapps.iddogchallenge.dog.data.DogsRepositoryImpl
import com.vctapps.iddogchallenge.dog.data.throwables.NoDogs
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

class DogsRepositoryTest: BaseNetworkTest() {

    lateinit var dogsRepository: DogsRepository

    val HUSK_CATEGORY = "husk"

    @Before
    override fun setUp(){
        super.setUp()

        dogsRepository = DogsRepositoryImpl(api)
    }

    @Test
    fun  `check get successful dog list`(){
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(200)
        mockedResponse.setBody(getListResponse())

        mockServer.enqueue(mockedResponse)

        val observer = dogsRepository.getUrlAddressDogs(HUSK_CATEGORY)
                .test()
                .assertComplete()

        assertThat(observer.values()[0].size, equalTo(2))
    }

    @Test
    fun  `check get error dog list`(){
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(200)
        mockedResponse.setBody(getErrorResponse())

        mockServer.enqueue(mockedResponse)

        dogsRepository.getUrlAddressDogs("labra")
                .test()
                .assertError(NoDogs::class.java)

    }

    private fun getErrorResponse() = "{" +
            "\"category\":\"labra\"}"

    private fun getListResponse() = "{" +
            "\"category\":\"labrador\"," +
            "\"list\":[" +
            "\"https://dog.ceo/api/img/labrador/n02099712_1150.jpg\"," +
            "\"https://dog.ceo/api/img/labrador/n02099712_1200.jpg\"]}"

}
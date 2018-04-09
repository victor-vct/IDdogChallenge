package com.vctapps.iddogchallenge.login.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import com.vctapps.iddogchallenge.BaseNetworkTest
import com.vctapps.iddogchallenge.core.data.IDdogApi
import com.vctapps.iddogchallenge.login.data.remoteDataSource.IDdogDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.RemoteDataSource
import com.vctapps.iddogchallenge.login.data.throwables.CantDoSignIn
import com.vctapps.iddogchallenge.login.data.throwables.InvalidUser
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSourceTest: BaseNetworkTest(){

    lateinit var remoteDataSource: RemoteDataSource

    val INVALID_EMAIL = "123"

    val VALID_EMAIL = "test@mail.com"

    @Before
    override fun setUp(){
        super.setUp()

        remoteDataSource = IDdogDataSource(api)
    }

    @Test
    fun `check if can sign in`(){
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(200)
        mockedResponse.setBody(getMockLoginResponse())

        mockServer.enqueue(mockedResponse)

        remoteDataSource.login(VALID_EMAIL)
                .test()
                .assertNoErrors()
    }

    @Test
    fun `on error check if cant sign in with invalid email`(){
        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(400)
        mockedResponse.setBody(getMockLoginResponseError())

        mockServer.enqueue(mockedResponse)

        remoteDataSource
                .login(INVALID_EMAIL)
                .test()
                .assertError(InvalidUser::class.java)


    }

    fun getMockLoginResponseError() = "{\"error\":{\"message\":\"Email is not valid\"}}"

    fun getMockLoginResponse() = "{\"user\":{\"email\": \"test@mail.com\",\"token\": \"123456\"}}"
}
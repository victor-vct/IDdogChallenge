package com.vctapps.iddogchallenge

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.okhttp.mockwebserver.MockWebServer
import com.vctapps.iddogchallenge.core.data.IDdogApi
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseNetworkTest {

    lateinit var api: IDdogApi

    val mockServer = MockWebServer()

    @Before
    open fun setUp(){
        val okHttpClient = OkHttpClient.Builder()
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(mockServer.url("/").toString())
                .build()

        api = retrofit.create(IDdogApi::class.java)
    }

    @After
    open fun teardown(){
        mockServer.shutdown()
    }

}
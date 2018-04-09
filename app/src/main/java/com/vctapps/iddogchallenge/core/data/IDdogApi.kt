package com.vctapps.iddogchallenge.core.data

import com.vctapps.iddogchallenge.dog.data.entity.FeedResponse
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginRequest
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginResponse
import io.reactivex.Maybe
import retrofit2.Response
import retrofit2.http.*

interface IDdogApi {

    @POST("signup")
    @Headers("Content-Type: application/json")
    fun login(@Body body: LoginRequest): Maybe<Response<LoginResponse>>

    @GET("feed")
    @Headers("Content-Type: application/json")
    fun feed(@Query("category") category: String): Maybe<Response<FeedResponse>>

}
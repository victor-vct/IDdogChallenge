package com.vctapps.iddogchallenge.core.data

import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginRequest
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginResponse
import io.reactivex.Maybe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IDdogApi {

    @POST("signup")
    @Headers("Content-Type: application/json")
    fun login(@Body body: LoginRequest): Maybe<Response<LoginResponse>>

}
package com.vctapps.iddogchallenge.login.data.remoteDataSource

import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginResponse
import io.reactivex.Maybe

interface RemoteDataSource {

    fun login(email: String): Maybe<LoginResponse>

}
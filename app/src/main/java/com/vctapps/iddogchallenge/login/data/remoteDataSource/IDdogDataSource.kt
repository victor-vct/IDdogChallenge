package com.vctapps.iddogchallenge.login.data.remoteDataSource

import com.vctapps.iddogchallenge.core.data.IDdogApi
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginRequest

class IDdogDataSource(private val api: IDdogApi): RemoteDataSource {

    override fun login(email: String) = api.login(LoginRequest(email))

}
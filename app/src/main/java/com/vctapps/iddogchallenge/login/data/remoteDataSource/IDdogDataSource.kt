package com.vctapps.iddogchallenge.login.data.remoteDataSource

import com.vctapps.iddogchallenge.core.data.IDdogApi
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginRequest
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginResponse
import com.vctapps.iddogchallenge.login.data.throwables.CantDoSignIn
import com.vctapps.iddogchallenge.login.data.throwables.InvalidUser
import io.reactivex.Maybe
import retrofit2.Response

class IDdogDataSource(private val api: IDdogApi): RemoteDataSource {

    //TODO tech debt: find a better way to check errors returns from this api that has only this error to login
    val INVALID_USER = "{\"error\":{\"message\":\"Email is not valid\"}}"

    override fun login(email: String) =
            api.login(LoginRequest(email))
                    .flatMap { response ->
                        if(response.isSuccessful){
                            Maybe.just(response.body())
                        }else {
                            proccessError(response)
                        }
                    }

    private fun proccessError(response: Response<LoginResponse>): Maybe<LoginResponse>? {
        return if (response.errorBody().string() == INVALID_USER) {
            Maybe.error(InvalidUser())
        } else {
            Maybe.error(CantDoSignIn())
        }
    }

}
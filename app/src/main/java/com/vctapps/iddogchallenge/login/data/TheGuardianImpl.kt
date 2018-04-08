package com.vctapps.iddogchallenge.login.data

import com.vctapps.iddogchallenge.core.ext.isValidString
import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.RemoteDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginResponse
import io.reactivex.Completable
import io.reactivex.Maybe

class TheGuardianImpl(private val localDataSource: LocalDataSource,
                      private val remoteDataSource: RemoteDataSource): TheGuardian{

    override fun getLoggedToken(): Maybe<String> {
        return localDataSource.getToken()
    }

    override fun checkUserCanSignIn(user: String): Completable {
        return remoteDataSource.login(user)
                .flatMapCompletable(this::saveLocalToken)
    }

    private fun saveLocalToken(response: LoginResponse) =
            localDataSource.saveToken(response.email, response.token)

    override fun checkCanSignIn(): Maybe<Boolean> {
        return localDataSource.getToken()
                .flatMap { response -> Maybe.just(response.isValidString()) }
    }
}
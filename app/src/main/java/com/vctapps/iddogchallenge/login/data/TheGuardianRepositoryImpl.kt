package com.vctapps.iddogchallenge.login.data

import com.vctapps.iddogchallenge.core.ext.isValidString
import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.RemoteDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.entity.LoginResponse
import io.reactivex.Completable
import io.reactivex.Maybe

class TheGuardianRepositoryImpl(private val localDataSource: LocalDataSource,
                                private val remoteDataSource: RemoteDataSource): TheGuardianRepository{

    override fun getToken() = localDataSource.getToken()

    override fun saveUser(user: String): Completable {
        return remoteDataSource.login(user)
                .flatMapCompletable(this::saveLocalToken)
    }

    private fun saveLocalToken(response: LoginResponse) =
            localDataSource.saveToken(response.user.email, response.user.token)

    override fun hasUserStored(): Maybe<Boolean> {
        return localDataSource.getToken()
                .flatMap { response -> Maybe.just(response.isValidString()) }
    }

    override fun clearToken() = localDataSource.deleteToken()
}


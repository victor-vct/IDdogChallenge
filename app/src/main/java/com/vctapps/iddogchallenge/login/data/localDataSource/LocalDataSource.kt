package com.vctapps.iddogchallenge.login.data.localDataSource

import io.reactivex.Completable
import io.reactivex.Maybe

interface LocalDataSource {

    fun saveToken(user: String, token: String): Completable

    fun getToken(): Maybe<String>

    fun deleteToken(): Completable

}
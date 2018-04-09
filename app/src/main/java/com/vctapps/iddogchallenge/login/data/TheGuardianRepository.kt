package com.vctapps.iddogchallenge.login.data

import io.reactivex.Completable
import io.reactivex.Maybe

interface TheGuardianRepository {

    fun getToken(): Maybe<String>

    fun saveUser(user: String): Completable

    fun hasUserStored(): Maybe<Boolean>

    fun clearToken(): Completable

}
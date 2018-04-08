package com.vctapps.iddogchallenge.login.domain

import io.reactivex.Completable
import io.reactivex.Maybe

interface TheGuardian {

    fun getToken(): Maybe<String>

    fun doLogin(user: String): Completable

    fun checkCanComeIn(): Maybe<Boolean>

}
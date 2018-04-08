package com.vctapps.iddogchallenge.login.data

import io.reactivex.Completable
import io.reactivex.Maybe

interface TheGuardian {

    fun getLoggedToken(): Maybe<String>

    fun checkUserCanSignIn(user: String): Completable

    fun checkCanSignIn(): Maybe<Boolean>

}
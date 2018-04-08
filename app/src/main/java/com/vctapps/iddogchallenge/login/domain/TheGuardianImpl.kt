package com.vctapps.iddogchallenge.login.domain

import com.vctapps.iddogchallenge.login.data.TheGuardianRepository

class TheGuardianImpl(private val repository: TheGuardianRepository): TheGuardian {

    override fun getToken() = repository.getToken()

    override fun doLogin(user: String) = repository.saveUser(user)

    override fun checkCanComeIn() = repository.hasUserStored()

}
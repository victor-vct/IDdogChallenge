package com.vctapps.iddogchallenge.login.presentation.presenter

import com.vctapps.iddogchallenge.core.presentation.BasePresenter

interface LoginPresenter: BasePresenter {

    fun onClickedLogin(user: String)

}
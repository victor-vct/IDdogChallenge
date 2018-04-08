package com.vctapps.iddogchallenge.login.presentation.view

import com.vctapps.iddogchallenge.core.presentation.BaseView

interface LoginView: BaseView {

    fun showEmailInvalid()

    fun hideEmailInvalid()

    fun goToDashboard()

}
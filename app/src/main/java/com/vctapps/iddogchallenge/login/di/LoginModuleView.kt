package com.vctapps.iddogchallenge.login.di

import com.vctapps.iddogchallenge.login.presentation.view.LoginActivity
import com.vctapps.iddogchallenge.login.presentation.view.LoginView
import dagger.Binds
import dagger.Module

@Module
abstract class LoginModuleView {

    @Binds
    abstract fun bindLoginActivity(activity: LoginActivity): LoginView

}
package com.vctapps.iddogchallenge.login.di

import com.vctapps.iddogchallenge.login.presentation.view.LoginActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Subcomponent(
        modules = [
            LoginModule::class,
            LoginModuleView::class
        ]
)
interface LoginComponent: AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<LoginActivity>()

}
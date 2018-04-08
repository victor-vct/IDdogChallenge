package com.vctapps.iddogchallenge.core.di

import android.app.Activity
import com.vctapps.iddogchallenge.dashboard.di.DashboardComponent
import com.vctapps.iddogchallenge.dashboard.presentation.view.DashboardActivity
import com.vctapps.iddogchallenge.login.di.LoginComponent
import com.vctapps.iddogchallenge.login.presentation.view.LoginActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuilder{

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity::class)
    abstract fun bindLoginActivity(builder: LoginComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(DashboardActivity::class)
    abstract fun bindDashboardActivity(builder: DashboardComponent.Builder): AndroidInjector.Factory<out Activity>

}
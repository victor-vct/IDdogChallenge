package com.vctapps.iddogchallenge.dashboard.di

import com.vctapps.iddogchallenge.dashboard.presentation.view.DashboardActivity
import com.vctapps.iddogchallenge.dashboard.presentation.view.DashboardView
import dagger.Binds
import dagger.Module

@Module
abstract class DashboardModuleView {

    @Binds
    abstract fun bindDashboardView(activity: DashboardActivity): DashboardView

}
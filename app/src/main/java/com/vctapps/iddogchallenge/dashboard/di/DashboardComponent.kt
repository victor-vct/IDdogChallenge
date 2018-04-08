package com.vctapps.iddogchallenge.dashboard.di

import com.vctapps.iddogchallenge.dashboard.presentation.view.DashboardActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [
    DashboardModule::class,
    DashboardModuleView::class
])
interface DashboardComponent: AndroidInjector<DashboardActivity> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<DashboardActivity>()

}
package com.vctapps.iddogchallenge.dashboard.di

import com.vctapps.iddogchallenge.dashboard.presentation.presenter.DashboardPresenter
import com.vctapps.iddogchallenge.dashboard.presentation.presenter.DashboardPresenterImpl
import com.vctapps.iddogchallenge.dashboard.presentation.view.DashboardView
import com.vctapps.iddogchallenge.login.domain.TheGuardian
import dagger.Module
import dagger.Provides

@Module
class DashboardModule {

    @Provides
    fun providesDashboardPresenter(view: DashboardView,
                                   theGuardian: TheGuardian): DashboardPresenter =
            DashboardPresenterImpl(view, theGuardian)

}
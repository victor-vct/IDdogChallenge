package com.vctapps.iddogchallenge.dashboard.presentation.presenter

import com.vctapps.iddogchallenge.core.presentation.BasePresenter

interface DashboardPresenter: BasePresenter {

    fun onLogoutClicked()

    fun onAboutClicked()

    fun onPictureClicked()

}
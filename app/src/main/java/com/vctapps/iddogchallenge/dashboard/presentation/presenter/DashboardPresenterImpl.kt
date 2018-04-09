package com.vctapps.iddogchallenge.dashboard.presentation.presenter

import com.vctapps.iddogchallenge.dashboard.presentation.view.DashboardView
import com.vctapps.iddogchallenge.login.domain.TheGuardian
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DashboardPresenterImpl(private val view: DashboardView,
                             private val theGuardian: TheGuardian): DashboardPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun onLogoutClicked() {
        view.showLoading()

        compositeDisposable.add(
            theGuardian.revokeAccess()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.goToLogin()
                        view.close()
                    }, { error ->
                        Timber.e(error)
                        view.hideLoading()
                        view.showError()
                    })
        )
    }

    override fun onAboutClicked() {
        view.goToAbout()
    }

    override fun onStart() {
        view.hideLoading()
    }

    override fun onFinish() {
        compositeDisposable.dispose()
    }
}
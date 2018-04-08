package com.vctapps.iddogchallenge.login.presentation.presenter

import com.vctapps.iddogchallenge.login.data.throwables.CantDoSignIn
import com.vctapps.iddogchallenge.login.data.throwables.InvalidUser
import com.vctapps.iddogchallenge.login.domain.TheGuardian
import com.vctapps.iddogchallenge.login.presentation.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class LoginPresenterImpl(private val view: LoginView,
                         private val theGuardian: TheGuardian): LoginPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onStart() {
        view.showLoading()

        compositeDisposable.add(
                theGuardian.checkCanComeIn()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ canComeIn ->
                        if(canComeIn) {
                            goDashboard()
                        } else {
                            view.hideLoading()
                        }
                    }, { error ->
                        Timber.e(error)
                        view.showError()
                    }, {
                        view.hideLoading()
                    })
        )
    }

    private fun goDashboard() {
        view.goToDashboard()
        view.close()
    }

    override fun onFinish() {
        compositeDisposable.dispose()
    }

    override fun onClickedLogin(user: String) {
        view.hideEmailInvalid()
        view.showLoading()

        compositeDisposable.add(
                theGuardian.doLogin(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        goDashboard()
                    }, {error ->
                        proccessError(error)
                    })
        )
    }

    private fun proccessError(error: Throwable?) {
        when (error) {
            is InvalidUser -> {
                view.showEmailInvalid()
                view.hideLoading()
            }
            is CantDoSignIn -> {
                view.hideLoading()
                view.showError()
            }
        }
    }
}
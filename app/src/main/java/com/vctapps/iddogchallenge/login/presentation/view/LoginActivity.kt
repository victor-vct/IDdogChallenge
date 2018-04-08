package com.vctapps.iddogchallenge.login.presentation.view

import android.os.Bundle
import com.vctapps.iddogchallenge.R
import com.vctapps.iddogchallenge.core.presentation.BaseActivity
import com.vctapps.iddogchallenge.login.presentation.presenter.LoginPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        setUpLoginButton()
    }

    private fun setUpLoginButton() {
        buttonLogin.setOnClickListener {
            presenter.onClickedLogin(getUser())
        }
    }

    private fun getUser() = editTextEmailLogin.text.toString()

    override fun showEmailInvalid() {
        textInputLayoutLogin.error = "Email invalido"
        textInputLayoutLogin.isErrorEnabled = true
    }

    override fun hideEmailInvalid() {
        textInputLayoutLogin.isErrorEnabled = false
    }

    override fun goToDashboard() {
        Timber.d("Request go to dashboard")
    }

    override fun onPause() {
        presenter.onFinish()

        super.onPause()
    }

    override fun onResume() {
        super.onResume()

        presenter.onStart()
    }
}

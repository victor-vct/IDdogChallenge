package com.vctapps.iddogchallenge.login.presentation.view

import android.content.Intent
import android.os.Bundle
import com.vctapps.iddogchallenge.R
import com.vctapps.iddogchallenge.core.presentation.BaseActivity
import com.vctapps.iddogchallenge.dashboard.presentation.view.DashboardActivity
import com.vctapps.iddogchallenge.login.presentation.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
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
        textInputLayoutLogin.error = getString(R.string.invalid_email)
        textInputLayoutLogin.isErrorEnabled = true
    }

    override fun hideEmailInvalid() {
        textInputLayoutLogin.isErrorEnabled = false
    }

    override fun goToDashboard() {
        Timber.d("Request go to dashboard")
        startActivity(Intent(this, DashboardActivity::class.java))
    }

    override fun onDestroy() {
        presenter.onFinish()

        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

        presenter.onStart()
    }
}

package com.vctapps.iddogchallenge.core.presentation

import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.loading_view.*

abstract class BaseActivity: AppCompatActivity(), BaseView {

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
    }

    override fun showError() {
        errorView.visibility = View.GONE
    }

    override fun hideError() {
        errorView.visibility = View.GONE
    }

    override fun close() {
        finish()
    }
}
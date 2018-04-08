package com.vctapps.iddogchallenge.dashboard.presentation.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.vctapps.iddogchallenge.R
import com.vctapps.iddogchallenge.core.presentation.BaseActivity
import com.vctapps.iddogchallenge.dashboard.presentation.presenter.DashboardPresenter
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

class DashboardActivity : BaseActivity(), DashboardView {

    @Inject
    lateinit var presenter: DashboardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        setSupportActionBar(toolbar)

        supportActionBar?.title = getString(R.string.app_name)

        val adapter = DashboardAdapter(supportFragmentManager)

        viewPagerDashboard.adapter = adapter

        tabLayoutDashboard.setupWithViewPager(viewPagerDashboard)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.logout -> presenter.onLogoutClicked()
            R.id.about -> presenter.onAboutClicked()
        }

        return true
    }
}

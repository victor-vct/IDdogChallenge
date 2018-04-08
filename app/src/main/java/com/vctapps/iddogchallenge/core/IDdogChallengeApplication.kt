package com.vctapps.iddogchallenge.core

import android.app.Activity
import android.app.Application
import com.vctapps.iddogchallenge.BuildConfig
import com.vctapps.iddogchallenge.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class IDdogChallengeApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        intiDI()

        initLogger()
    }

    private fun intiDI() {
        val appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()

        appComponent.inject(this)
    }

    private fun initLogger() {
        //TODO add crash reports
        Timber.plant(Timber.DebugTree())
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

}
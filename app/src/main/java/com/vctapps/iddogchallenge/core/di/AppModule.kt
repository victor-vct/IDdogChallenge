package com.vctapps.iddogchallenge.core.di

import android.app.Application
import com.vctapps.iddogchallenge.login.di.LoginComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [
    LoginComponent::class
])
class AppModule {

    @Provides
    fun providesApplicationContext(application: Application) = application.applicationContext

}
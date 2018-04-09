package com.vctapps.iddogchallenge.core.di

import android.app.Application
import com.vctapps.iddogchallenge.core.IDdogChallengeApplication
import com.vctapps.iddogchallenge.login.di.LoginModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    AppModule::class,
    LoginModule::class,
    NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: IDdogChallengeApplication)

}
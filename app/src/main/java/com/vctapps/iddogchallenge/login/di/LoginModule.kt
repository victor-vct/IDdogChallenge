package com.vctapps.iddogchallenge.login.di

import android.accounts.AccountManager
import android.content.Context
import com.vctapps.iddogchallenge.core.data.IDdogApi
import com.vctapps.iddogchallenge.login.data.TheGuardianRepository
import com.vctapps.iddogchallenge.login.data.TheGuardianRepositoryImpl
import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSource
import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSourceImpl
import com.vctapps.iddogchallenge.login.data.remoteDataSource.IDdogDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.RemoteDataSource
import com.vctapps.iddogchallenge.login.domain.TheGuardian
import com.vctapps.iddogchallenge.login.domain.TheGuardianImpl
import com.vctapps.iddogchallenge.login.presentation.presenter.LoginPresenter
import com.vctapps.iddogchallenge.login.presentation.presenter.LoginPresenterImpl
import com.vctapps.iddogchallenge.login.presentation.view.LoginView
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun providesLoginPresenter(view: LoginView,
                               theGuardian: TheGuardian): LoginPresenter =
            LoginPresenterImpl(view, theGuardian)

    @Provides
    fun providesTheGuardian(repository: TheGuardianRepository): TheGuardian =
            TheGuardianImpl(repository)

    @Provides
    fun providesTheGuardianRepository(localDataSource: LocalDataSource,
                                      remoteDataSource: RemoteDataSource): TheGuardianRepository =
            TheGuardianRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    fun providesLocalDataSource(accountManager: AccountManager): LocalDataSource =
            LocalDataSourceImpl(accountManager)

    @Provides
    fun providesAccountManager(context: Context) = AccountManager.get(context)

    @Provides
    fun providesRemoteDataSource(api: IDdogApi): RemoteDataSource =
            IDdogDataSource(api)

}
package com.vctapps.iddogchallenge.login.di

import android.accounts.AccountManager
import android.content.Context
import com.vctapps.iddogchallenge.core.data.IDdogApi
import com.vctapps.iddogchallenge.login.data.TheGuardian
import com.vctapps.iddogchallenge.login.data.TheGuardianImpl
import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSource
import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSourceImpl
import com.vctapps.iddogchallenge.login.data.remoteDataSource.IDdogDataSource
import com.vctapps.iddogchallenge.login.data.remoteDataSource.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun providesTheGuardian(localDataSource: LocalDataSource,
                            remoteDataSource: RemoteDataSource): TheGuardian =
            TheGuardianImpl(localDataSource, remoteDataSource)

    @Provides
    fun providesLocalDataSource(accountManager: AccountManager): LocalDataSource =
            LocalDataSourceImpl(accountManager)

    @Provides
    fun providesAccountManager(context: Context) = AccountManager.get(context)

    @Provides
    fun providesRemoteDataSource(api: IDdogApi): RemoteDataSource =
            IDdogDataSource(api)

}
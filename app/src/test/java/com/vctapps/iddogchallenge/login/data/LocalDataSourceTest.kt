package com.vctapps.iddogchallenge.login.data

import android.accounts.Account
import android.accounts.AccountManager
import com.vctapps.iddogchallenge.login.data.localDataSource.AuthenticatorManager
import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSource
import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSourceImpl
import com.vctapps.iddogchallenge.login.data.throwables.InvalidToken
import com.vctapps.iddogchallenge.login.data.throwables.UserAlreadyExists
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.BDDMockito.*
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class LocalDataSourceTest {

    lateinit var localDataSource: LocalDataSource

    val accountManager = mock(AccountManager::class.java)

    val VALID_USER = "test@mail.com"

    val VALID_TOKEN = "123"

    val account = mock(Account::class.java)

    val accounts = arrayOf(account)

    @Before
    fun setUp(){
        localDataSource = LocalDataSourceImpl(accountManager)
    }

    @Test
    fun `check save with successful`(){
        given(accountManager
                .addAccountExplicitly(ArgumentMatchers.any(Account::class.java), ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .willReturn(true)

        Mockito.doNothing()
                .`when`(accountManager)
                .setAuthToken(accounts[0], AuthenticatorManager.TOKEN_TYPE, VALID_TOKEN)

        given(accountManager.getAccountsByType(LocalDataSourceImpl.ACCOUNT_TYPE))
                .willReturn(arrayOf())

        localDataSource.saveToken(VALID_USER, VALID_TOKEN)
                .test()
                .assertComplete()

    }

    @Test
    fun `check cant save if user already exist`(){
        given(accountManager.getAccountsByType(LocalDataSourceImpl.ACCOUNT_TYPE))
                .willReturn(accounts)

        localDataSource.saveToken(VALID_USER, VALID_TOKEN)
                .test()
                .assertError(UserAlreadyExists::class.java)
    }

    @Test
    fun `check cant save with invalid token`(){
        given(accountManager.getAccountsByType(LocalDataSourceImpl.ACCOUNT_TYPE))
                .willReturn(arrayOf())

        localDataSource.saveToken(VALID_USER, "")
                .test()
                .assertError(InvalidToken::class.java)
    }

    @Test
    fun `check get token with successful`(){
        given(accountManager.getAccountsByType(LocalDataSourceImpl.ACCOUNT_TYPE))
                .willReturn(accounts)

        `when`(accountManager.blockingGetAuthToken(account, AuthenticatorManager.TOKEN_TYPE, true))
                .thenReturn(VALID_TOKEN)

        localDataSource.getToken()
                .test()
                .assertValue(VALID_TOKEN)
    }

    @Test
    fun `check just complete when there is no token`(){
        given(accountManager.getAccountsByType(LocalDataSourceImpl.ACCOUNT_TYPE))
                .willReturn(arrayOf())

        localDataSource.getToken()
                .test()
                .assertComplete()
                .assertValueCount(0)
    }

    @Test
    fun `check clear token with successful`(){
        given(accountManager.getAccountsByType(LocalDataSourceImpl.ACCOUNT_TYPE))
                .willReturn(accounts)

        localDataSource.deleteToken()
                .test()
                .assertComplete()


        Mockito.verify(accountManager, times(1)).removeAccount(account, null, null)
    }

    @Test
    fun `check complete when there is no token`(){
        given(accountManager.getAccountsByType(LocalDataSourceImpl.ACCOUNT_TYPE))
                .willReturn(arrayOf())

        localDataSource.deleteToken()
                .test()
                .assertComplete()

        Mockito.verify(accountManager, times(0)).removeAccount(account, null, null)
    }

}
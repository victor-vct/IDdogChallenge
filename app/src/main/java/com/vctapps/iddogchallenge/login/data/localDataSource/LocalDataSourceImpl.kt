package com.vctapps.iddogchallenge.login.data.localDataSource

import android.accounts.Account
import android.accounts.AccountManager
import com.vctapps.iddogchallenge.BuildConfig
import com.vctapps.iddogchallenge.core.ext.isNotValidString
import com.vctapps.iddogchallenge.core.ext.isValidString
import com.vctapps.iddogchallenge.login.data.throwables.InvalidToken
import com.vctapps.iddogchallenge.login.data.throwables.UserAlreadyExists
import io.reactivex.Completable
import io.reactivex.Maybe
import timber.log.Timber

class LocalDataSourceImpl(private val accountManager: AccountManager): LocalDataSource {

    companion object {
        val ACCOUNT_TYPE = BuildConfig.APPLICATION_ID
    }

    override fun saveToken(user: String, token: String): Completable {
        return Completable.create { emitter ->
            val accounts = accountManager.getAccountsByType(ACCOUNT_TYPE)

            if(accounts?.size != 0) emitter.onError(UserAlreadyExists())

            if (token.isNotValidString()) {

                Timber.d("Invalid token")

                emitter.onError(InvalidToken())

            } else {

                val newAccount = Account(user, ACCOUNT_TYPE)

                accountManager.addAccountExplicitly(newAccount, null, null)
                accountManager.setAuthToken(newAccount, AuthenticatorManager.TOKEN_TYPE, token)

                Timber.d("Account save successful. User: " + newAccount.name)

                emitter.onComplete()

            }

        }
    }

    override fun getToken(): Maybe<String> {
        return Maybe.create { emitter ->

            if (accountManager.getAccountsByType(ACCOUNT_TYPE)?.size == 0) {
                emitter.onComplete()
            } else {
                val account = accountManager.getAccountsByType(ACCOUNT_TYPE)[0]

                val token = accountManager.blockingGetAuthToken(account,
                        AuthenticatorManager.TOKEN_TYPE,
                        true)

                if (token.isValidString()) {
                    emitter.onSuccess(token)
                } else {
                    emitter.onComplete()
                }
            }

        }
    }

    override fun deleteToken(): Completable {
        return Completable.create{ emitter ->

            if (accountManager.getAccountsByType(ACCOUNT_TYPE)?.size == 0) {

                emitter.onComplete()

            } else {

                accountManager.getAccountsByType(ACCOUNT_TYPE).forEach { account ->
                    if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1)
                        accountManager.removeAccountExplicitly(account)
                    else
                        accountManager.removeAccount(account, null, null)
                }

                emitter.onComplete()
            }

        }
    }
}
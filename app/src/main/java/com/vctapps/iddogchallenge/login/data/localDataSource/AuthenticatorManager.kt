package com.vctapps.iddogchallenge.login.data.localDataSource

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Intent
import android.os.Bundle
import com.vctapps.iddogchallenge.core.IDdogChallengeApplication
import com.vctapps.iddogchallenge.login.presentation.view.LoginActivity

class AuthenticatorManager(private val application: IDdogChallengeApplication):
        AbstractAccountAuthenticator(application) {

    companion object {
        val ADD_ACCOUNT = "addAccount"

        val TOKEN_TYPE = "tokenType"
    }

    override fun getAuthTokenLabel(p0: String?) = TOKEN_TYPE

    override fun confirmCredentials(response: AccountAuthenticatorResponse?,
                                    account: Account?,
                                    options: Bundle?) = Bundle()

    override fun updateCredentials(p0: AccountAuthenticatorResponse?,
                                   p1: Account?,
                                   p2: String?,
                                   p3: Bundle?) = Bundle()

    override fun getAuthToken(response: AccountAuthenticatorResponse?,
                              account: Account?,
                              authTokenType: String?,
                              options: Bundle?): Bundle {

        val accountManager = AccountManager.get(application)

        val authToken = accountManager.peekAuthToken(account, authTokenType)

        if(authToken.isNullOrEmpty()){
            val result = Bundle()
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken)
            return result
        }

        val openLoginActivityIntent = Intent(application, LoginActivity::class.java)

        openLoginActivityIntent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        openLoginActivityIntent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
        openLoginActivityIntent.putExtra(AccountManager.KEY_ACCOUNT_NAME, account?.name)
        openLoginActivityIntent.putExtra(TOKEN_TYPE, authTokenType)

        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, openLoginActivityIntent)

        return bundle
    }

    override fun hasFeatures(response: AccountAuthenticatorResponse?,
                             account: Account?,
                             p2: Array<out String>?): Bundle {
        val result = Bundle()
        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false)
        return result
    }

    override fun editProperties(response: AccountAuthenticatorResponse?,
                                p1: String?) = Bundle()

    override fun addAccount(response: AccountAuthenticatorResponse?,
                            accountType: String?,
                            authTokenType: String?,
                            requiredFeatures: Array<out String>?,
                            options: Bundle?): Bundle {

        val intent = Intent(application, LoginActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType)
        intent.putExtra(ADD_ACCOUNT, true)
        intent.putExtra(TOKEN_TYPE, authTokenType)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)

        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)

        return bundle
    }

}
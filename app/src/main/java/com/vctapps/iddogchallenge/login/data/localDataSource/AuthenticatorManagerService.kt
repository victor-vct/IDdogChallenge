package com.vctapps.iddogchallenge.login.data.localDataSource

import android.app.Service
import android.content.Intent
import com.vctapps.iddogchallenge.core.IDdogChallengeApplication

class AuthenticatorManagerService: Service() {

    override fun onBind(p0: Intent?) =
            AuthenticatorManager(application as IDdogChallengeApplication).iBinder

}
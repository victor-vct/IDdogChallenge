package com.vctapps.iddogchallenge.about

import android.content.Intent
import android.net.Uri

object IntentContact {

    private const val GITHUB_PROJECT = "https://github.com/victor-vct/IDdogChallenge"

    private const val EMAIL_ADDRESS = "victorvieira302@gmail.com"

    fun getEmailIntent(): Intent {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",EMAIL_ADDRESS, null))

        return Intent.createChooser(intent, "email")
    }

    fun getProjectPage() = Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_PROJECT))

}
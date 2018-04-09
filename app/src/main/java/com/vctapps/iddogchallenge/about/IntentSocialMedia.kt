package com.vctapps.iddogchallenge.about

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

object IntentSocialMedia {

    private const val FACEBOOK_PACKAGE = "com.facebook.katana"
    private const val FACEBOOK_PROFILE_APP = "fb://profile/victor.vieira.75470"
    private const val FACEBOOK_PROFILE_WEB = "https://www.facebook.com/victor.vieira.75470"

    private const val LINKEDIN_PROFILE_WEB = "https://www.linkedin.com/in/victor-vieira-0a1b38bb"

    private const val GITHUB_PROFILE_WEB = "https://github.com/victor-vct"

    fun facebook(context: Context) = getAppSocialIntent(FACEBOOK_PACKAGE,
                FACEBOOK_PROFILE_APP,
                FACEBOOK_PROFILE_WEB,
                context)

    fun linkedin() = Intent(Intent.ACTION_VIEW, Uri.parse(LINKEDIN_PROFILE_WEB))

    fun github() = Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_PROFILE_WEB))

    private fun getAppSocialIntent(packageApp: String,
                                   profileApp: String,
                                   profileWeb: String,
                                   context: Context): Intent {
        return try {

            context.packageManager.getPackageInfo(packageApp, 0)

            Intent(Intent.ACTION_VIEW, Uri.parse(profileApp))

        } catch (e: PackageManager.NameNotFoundException) {

            Intent(Intent.ACTION_VIEW, Uri.parse(profileWeb))

        }
    }

}
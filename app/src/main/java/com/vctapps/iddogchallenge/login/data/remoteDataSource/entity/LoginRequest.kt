package com.vctapps.iddogchallenge.login.data.remoteDataSource.entity

import com.google.gson.annotations.SerializedName

data class LoginRequest(@SerializedName("email") val email: String)
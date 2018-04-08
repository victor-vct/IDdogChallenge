package com.vctapps.iddogchallenge.login.data.remoteDataSource.entity

import com.vctapps.iddogchallenge.core.domain.InvalidData

data class UserResponse(var email: String = InvalidData.INVALID_STRING,
                        var token: String = InvalidData.INVALID_STRING)
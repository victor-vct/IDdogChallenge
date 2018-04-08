package com.vctapps.iddogchallenge.login.data.remoteDataSource.entity

import com.vctapps.iddogchallenge.core.data.ErrorResponse

data class LoginResponse(var user: UserResponse = UserResponse(),
                         var error: ErrorResponse = ErrorResponse.invalidErrorResponse())
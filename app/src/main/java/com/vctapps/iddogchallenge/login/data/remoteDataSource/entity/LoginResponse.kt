package com.vctapps.iddogchallenge.login.data.remoteDataSource.entity

import com.vctapps.iddogchallenge.core.data.ErrorResponse
import com.vctapps.iddogchallenge.core.domain.InvalidData

data class LoginResponse(var email: String = InvalidData.INVALID_STRING,
                         var token: String = InvalidData.INVALID_STRING,
                         var error: ErrorResponse = ErrorResponse.invalidErrorResponse())
package com.vctapps.iddogchallenge.core.data

import com.vctapps.iddogchallenge.core.domain.InvalidData

data class ErrorResponse(val message: String = ""){

    companion object {
        fun invalidErrorResponse() = ErrorResponse(InvalidData.INVALID_STRING)
    }

}
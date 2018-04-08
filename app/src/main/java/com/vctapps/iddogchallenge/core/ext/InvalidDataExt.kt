package com.vctapps.iddogchallenge.core.ext

import com.vctapps.iddogchallenge.core.domain.InvalidData

fun String.isValidString(): Boolean{
    return this != InvalidData.INVALID_STRING
}

fun String.isNotValidString(): Boolean{
    return this == InvalidData.INVALID_STRING
}
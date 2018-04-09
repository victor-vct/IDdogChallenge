package com.vctapps.iddogchallenge.dog.data

import io.reactivex.Maybe

interface DogsRepository {

    fun getUrlAddressDogs(category: String): Maybe<MutableList<String>>

}
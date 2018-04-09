package com.vctapps.iddogchallenge.dog.data.entity

import com.google.gson.annotations.SerializedName
import com.vctapps.iddogchallenge.core.domain.InvalidData

data class FeedResponse(@SerializedName("category") val category: String = InvalidData.INVALID_STRING,
                        @SerializedName("list") val urls: MutableList<String> = mutableListOf())
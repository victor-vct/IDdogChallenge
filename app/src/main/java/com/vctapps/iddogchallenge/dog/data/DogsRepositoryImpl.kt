package com.vctapps.iddogchallenge.dog.data

import com.vctapps.iddogchallenge.core.data.IDdogApi
import com.vctapps.iddogchallenge.dog.data.entity.FeedResponse
import com.vctapps.iddogchallenge.dog.data.throwables.NoDogs
import io.reactivex.Maybe
import retrofit2.Response

class DogsRepositoryImpl(private val api: IDdogApi): DogsRepository {

    override fun getUrlAddressDogs(category: String): Maybe<MutableList<String>> {
        return api.feed(category)
                .flatMap (this::proccessResponse)
    }

    private fun proccessResponse(response: Response<FeedResponse>): Maybe<MutableList<String>>? {
        return if (response.isSuccessful) {
            val list = response.body().urls
            if (list.size == 0) Maybe.error(NoDogs()) else Maybe.just(list)
        } else {
            Maybe.error(NoDogs())
        }
    }

}
package com.vctapps.iddogchallenge.core.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vctapps.iddogchallenge.core.data.IDdogApi
import com.vctapps.iddogchallenge.login.data.localDataSource.LocalDataSource
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    val BASE_URL = "https://iddog-api.now.sh/"

    @Provides
    fun providesIDdogApi(retrofit: Retrofit): IDdogApi {
        return retrofit.create<IDdogApi>(IDdogApi::class.java)
    }


    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun providesOkHttpClientDashboard(loggingInterceptor: HttpLoggingInterceptor,
                             datasourceToken: LocalDataSource): OkHttpClient {

        val okhttp = OkHttpClient.Builder()

        addLoggingInterceptor(okhttp, loggingInterceptor)

        addHeaderInterceptor(datasourceToken, okhttp)

        return okhttp.build()
    }

    private fun addLoggingInterceptor(okhttp: OkHttpClient.Builder,
                                      loggingInterceptor: HttpLoggingInterceptor) {
        okhttp.addInterceptor(loggingInterceptor)
    }

    private fun addHeaderInterceptor(datasourceToken: LocalDataSource, okhttp: OkHttpClient.Builder) {
        okhttp.networkInterceptors().add(HeaderInterceptor(datasourceToken))
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY

        return logging
    }

    inner class HeaderInterceptor(private val datasourceToken: LocalDataSource): Interceptor{
        override fun intercept(chain: Interceptor.Chain?): Response {
            val token = datasourceToken.getToken()
                    .subscribeOn(Schedulers.io())
                    .blockingGet()

            val request = chain
                        ?.request()
                        ?.newBuilder()

            if(token != null) request?.addHeader("Authorization", token)

            return chain!!.proceed(request?.build())
        }
    }

}

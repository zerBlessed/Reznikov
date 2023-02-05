package com.reznikov.tinkofffinteh.data

import com.reznikov.tinkofffinteh.BuildConfig
import com.reznikov.tinkofffinteh.domain.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceImplementation {

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(getHttpClient())
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ApiService = retrofit.create(ApiService::class.java)

    private fun getHttpClient(): OkHttpClient {
        val httpLogInter = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLogInter.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpLogInter)
            .build()
    }
}
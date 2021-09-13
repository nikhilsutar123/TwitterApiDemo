package com.example.twitterapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object{
        val retrofit by lazy {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

           val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor).build()

            Retrofit.Builder()
                .baseUrl(TwitterApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()
        }

        val retrofitBuilder by lazy {
            retrofit.create(TwitterApi::class.java)
        }
    }
}
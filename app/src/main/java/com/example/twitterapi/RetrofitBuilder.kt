package com.example.twitterapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

  fun provideRetrofit() = Retrofit.Builder()
      .baseUrl(TwitterApi.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create()).build()
}
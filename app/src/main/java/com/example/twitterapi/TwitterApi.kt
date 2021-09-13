package com.example.twitterapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface TwitterApi {

    companion object {
        private const val BEARER_TOKEN = BuildConfig.TWITTER_BEARER_TOKEN
        const val BASE_URL = "https://api.twitter.com/"
    }

    @Headers("Accept: */*","Authorization: Bearer $BEARER_TOKEN")
    @GET("/2/users/by/username/{username}")
     fun searchUser(@Path("username") username: String): Call<TwitterData>
}
package com.example.hiltexample.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<String?>?>?
}
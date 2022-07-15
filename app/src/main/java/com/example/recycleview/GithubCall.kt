package com.example.recycleview

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//https://api.github.com/orgs/fossasia/repos


interface GithubInterface{
    @GET("/orgs/{ORG}/repos")
    suspend fun getRepos(@Path("ORG") ORG:String) : Response<List<Repos>>
}

object GithubCall {
    val baseUrl = "https://api.github.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
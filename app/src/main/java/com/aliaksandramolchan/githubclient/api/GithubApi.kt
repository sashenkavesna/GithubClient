package com.aliaksandramolchan.githubclient.api

import com.aliaksandramolchan.githubclient.model.Repo
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GithubApi {
    @GET("users/gojuno/repos")
    fun getRepos(): Observable<List<Repo>>

    companion object {
        fun create(): GithubApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()
            return retrofit.create(GithubApi::class.java)
        }
    }
}



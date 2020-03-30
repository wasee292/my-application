package com.example.myapplication.api

import com.example.myapplication.model.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
  @GET("/repositories")
  fun getRepos(
    @Query("language") language:String = "",
    @Query("since") since: String = "daily"
  ) : Single<MutableList<Repo>>
}

package com.example.myapplication.repository

import com.example.myapplication.network.NetworkHandler

class GithubRepository(private val networkHandler: NetworkHandler) {
  fun fetchRepos() = networkHandler.githubApi.getRepos()
}

package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class User(
  @SerializedName("avatar")
  val avatar: String,
  @SerializedName("href")
  val href: String,
  @SerializedName("username")
  val username: String
)

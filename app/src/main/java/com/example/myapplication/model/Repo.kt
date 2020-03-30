package com.example.myapplication.model

import android.graphics.Color
import com.google.gson.annotations.SerializedName

data class Repo(
  @SerializedName("author")
  val author: String,
  @SerializedName("avatar")
  val avatar: String,
  @SerializedName("builtBy")
  val user: List<User>,
  @SerializedName("currentPeriodStars")
  val currentPeriodStars: Int,
  @SerializedName("description")
  val description: String,
  @SerializedName("forks")
  val forks: Int,
  @SerializedName("language")
  val language: String,
  @SerializedName("languageColor")
  val languageColor: String,
  @SerializedName("name")
  val name: String,
  @SerializedName("stars")
  val stars: Int,
  @SerializedName("url")
  val url: String
) {
  var langColor = Color.rgb(
    Integer.valueOf(languageColor.substring(1, 3), 16),
    Integer.valueOf(languageColor.substring(3, 5), 16),
    Integer.valueOf(languageColor.substring(5, 7), 16)
  )

  var expand = false
}

package com.noman.retrofit.models

import com.google.gson.annotations.SerializedName

data class DataPhoto(
        @SerializedName("albumId")
        val albumId: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
)
package com.noman.retrofit.retrofit

import com.noman.retrofit.models.DataPhoto
import retrofit2.Call
import retrofit2.http.GET


interface GetDataService {

    @GET("/photos")
    fun getAllPhotos(): Call<ArrayList<DataPhoto>>

}
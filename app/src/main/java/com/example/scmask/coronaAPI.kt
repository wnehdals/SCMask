package com.example.scmask

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface coronaAPI{
    @GET("storesByGeo/json")
    fun getMaskData(@Query("lat") lat : String, @Query("lng") lng : String, @Query("m") m : Int) : Call<StoreSaleResult>
}
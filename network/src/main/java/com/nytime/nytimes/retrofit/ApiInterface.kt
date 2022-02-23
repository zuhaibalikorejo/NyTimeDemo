package com.nytime.nytimes.retrofit

import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("{end_point}")
    fun getRequest(
        @Path(value = "end_point", encoded = true) endPoint: String?,
        @Query("api-key") apiKey: String?

    ): Call<String?>?

}
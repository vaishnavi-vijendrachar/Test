package com.vaishnavi.repository


import com.vaishnavi.model.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val URL = "https://dl.dropboxusercontent.com"
class ApiClient {
    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val buildService: RemoteService = getRetrofitInstance()
    .create(RemoteService::class.java)
}

interface RemoteService {
    @GET("/s/2iodh4vg0eortkl/facts.js")
    suspend fun getDataFromRemote() : Response
}
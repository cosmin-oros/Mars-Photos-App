package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofit builder to build and create a retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//interface that defines how Retrofit talks to the web server using HTTP requests
interface MarsApiService{
    //get request
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto> //suspend -> so that you can call it within a coroutine
}

//public singleton object that can be accessed from the rest of the app
//initialize the Retrofit service
object MarsApi{
    //lazy initialization to make sure it is initialized at its first usage
    val retrofitService: MarsApiService by lazy{
        retrofit.create(MarsApiService::class.java)
    }
}

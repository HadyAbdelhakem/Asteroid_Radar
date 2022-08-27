package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

enum class AsteroidFilter(val value: String) {
    VIEW_TODAY(Constants.START_DATE),
    VIEW_WEEK(Constants.END_DATE),
    VIEW_SAVED(Constants.END_DATE)
}


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .build()
private val picRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .build()


interface AsteroidApiService {

    @GET("neo/rest/v1/feed?")
    suspend fun getAsteroid(
        @Query("start_date") start_date : String,
        @Query("end_date") end_date : String,
        @Query("api_key") api_key : String,

    ) : String

    @GET("planetary/apod?")
    fun getPictureOfDay(
        @Query("api_key") api_key : String
    ): Call<PictureOfDay>
}

object NasaApi {
    val retrofitService : AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
}

object PictureOfDayApi {
    val picRetrofitService : AsteroidApiService by lazy {
        picRetrofit.create(AsteroidApiService::class.java)
    }
}

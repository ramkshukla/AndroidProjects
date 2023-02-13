package com.example.task4

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL =
    "https://weather.visualcrossing.com/"
interface WeatherInterface {
    @GET("VisualCrossingWebServices/rest/services/timeline/{location}") //{sdate}/{edate}
    fun getWeatherData(@Path("location")location: String, @Query("key")key :String): Call<ApiData>  //@Path("sdate")sdate: String, @Path("edate")edate: String,
}

object WeatherService {
    val weatherInstance: WeatherInterface

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        weatherInstance = retrofit.create(WeatherInterface::class.java)
    }

}

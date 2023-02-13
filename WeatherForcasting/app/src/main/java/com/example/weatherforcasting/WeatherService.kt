package com.example.weatherforcasting

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://api.weatherapi.com/v1"
const val API_KEY = "f086109eab594c9db1e22717222306"
interface WeatherInterface {

    @GET("/current.json?apiKey=$API_KEY")
    fun getCurrentWeather(@Query("q")q: String, @Query("aqi")aqi: String): Call<locationDetails>

}


object  WeatherService{
    val weatherInstance: WeatherInterface
    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        weatherInstance = retrofit.create(WeatherInterface::class.java)
    }
}
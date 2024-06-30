package com.example.weaitherapplication.Network

import com.example.weaitherapplication.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApplication
{
    @GET("weather")
    fun getWeatherData(@Query("q") city: String, @Query("appid") appid: String, @Query("units") units: String):Call<Weather>
}
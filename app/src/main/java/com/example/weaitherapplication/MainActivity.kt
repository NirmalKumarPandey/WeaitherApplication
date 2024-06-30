package com.example.weaitherapplication


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weaitherapplication.Network.WeatherApplication
import com.example.weaitherapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.getTemperature.setOnClickListener {
            fetchWeatherData(binding.cityName.text.toString());
        }
    }
    private fun fetchWeatherData(name: String)
    {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherApplication::class.java);
        val response=retrofit.getWeatherData(city = name, appid = "3d0f68fc891acbac07496289fcc74c56", units = "metric");
        response.enqueue(object :Callback<Weather>{
            override fun onResponse(call: Call<Weather>, response: Response<Weather>)
            {
                  val responseBody=response.body();
                  if(response.isSuccessful && responseBody!=null){
                      val temperature=responseBody.main.temp.toString();
                      val humidity=responseBody.main.humidity.toString();
                      val pressure=responseBody.main.pressure.toString();
                      val speed=responseBody.wind.speed.toString();
                      Log.d(TAG,"Temperature $temperature");
                      Toast.makeText(applicationContext, "Temperature=$temperature Humidity=$humidity Pressure=$pressure Speed=$speed",Toast.LENGTH_LONG).show();
                  }
            }

            override fun onFailure(call: Call<Weather>, result: Throwable)
            {
                Toast.makeText(applicationContext, "Result==$result",Toast.LENGTH_LONG).show();
            }
        });


    }


}
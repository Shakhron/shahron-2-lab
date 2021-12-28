package com.example.shahron2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {

    private lateinit var adapter: AdapRecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val listCity = listOf(
            "Kuala Lumpur",
            "Istanbul",
            "Paris",
            "Moscow",
            "Mumbai",
            "Rome",
            "New York",
            "Pattaya",
            "Moscow",
            "Seoul",
            "Kolkata",
            "Mugla",
            "Batam"
        )

        val listCityTemp = mutableListOf<Temp>()



        for (i in listCity) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service: TempService = retrofit.create(TempService::class.java)

            val call: Call<ResponseTemperature> =
                service.getSityWeather(i, "b77c723674fb2df88e432d822db3361f") as Call<ResponseTemperature>
            call.enqueue(object : Callback<ResponseTemperature?> {
                override fun onResponse(
                    call: Call<ResponseTemperature?>,
                    response: Response<ResponseTemperature?>
                ) {
                    if (response.code() == 200) {
                        val weatherResponse: ResponseTemperature = response.body()!!
                        val tempGradus = weatherResponse.main!!.temp.toDouble() - 273.15
                        val weatherNow = "Temperature: \n$tempGradus"
                        listCityTemp.add(Temp(i, weatherNow))

                        val rv = findViewById<RecyclerView>(R.id.recy)
                        rv.layoutManager = LinearLayoutManager(applicationContext)
                        adapter = AdapRecycle()
                        rv.adapter = adapter
                        adapter.array = listCityTemp

                    }
                }

                override fun onFailure(call: Call<ResponseTemperature?>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })

        }



    }
}
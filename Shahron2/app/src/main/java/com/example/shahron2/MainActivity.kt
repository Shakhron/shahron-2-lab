package com.example.shahron2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var text: TextView
    lateinit var btn1: Button
    lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn1 = findViewById(R.id.tempNowBtn)
        btn2 = findViewById(R.id.listTempBtn)
        text = findViewById(R.id.textid)

        btn1.setOnClickListener {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service: TempService = retrofit.create(TempService::class.java)

            val call: Call<ResponseTemperature> = service.getSityWeather("Moscow", "b77c723674fb2df88e432d822db3361f") as Call<ResponseTemperature>
            call.enqueue(object : Callback<ResponseTemperature?> {
                override fun onResponse(
                    call: Call<ResponseTemperature?>,
                    response: Response<ResponseTemperature?>
                ) {
                    if (response.code() == 200) {
                        val weather: ResponseTemperature = response.body()!!
                        val temp = weather.main!!.temp.toDouble() - 273.15
                        val tempNow ="Сейчас в Москве \n Temperature: $temp"
                        text.text = tempNow
                    }
                }

                override fun onFailure(call: Call<ResponseTemperature?>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })
        }

        btn2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }
}
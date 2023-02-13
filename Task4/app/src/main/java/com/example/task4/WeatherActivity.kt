package com.example.task4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class WeatherActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        apiCall()
        val signOutBtn: Button = findViewById(R.id.btn_sign_out)
        // Build a GoogleSignInClient with the options specified by gso
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this, gso)
        signOutBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                when (p0?.id) {
                    R.id.btn_sign_out -> signOut(mGoogleSignInClient)
                }
            }
        })
    }

    private fun signOut(mGoogleSignInClient: GoogleSignInClient) {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void?> {
                Toast.makeText(this, "User signout successfully", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            })
    }

    private fun apiCall() {
        val weather: Call<ApiData> =
            WeatherService.weatherInstance.getWeatherData("Noida,IN", "4W8HZ7MN397B6M7YS27JXPTZL")
        weather.enqueue(object : Callback<ApiData> {
            override fun onFailure(call: Call<ApiData>, t: Throwable) {

            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<ApiData>, response: Response<ApiData>) {
                Log.d("response ----->", response.body().toString())
                val res: ApiData? = response.body()
                val tempId: TextView = findViewById(R.id.temp_id)
                val max_tempId: TextView = findViewById(R.id.max_temp_id)
                val min_tempId: TextView = findViewById(R.id.min_temp_id)
                val weather_img: ImageView = findViewById(R.id.weather_img)
                val img_text: TextView = findViewById(R.id.img_text)
                val address: TextView = findViewById(R.id.address_id)
                if (res != null) {
                    address.text = res.address

//                    datetime: "2022-07-19"
                    val sdate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val sd = SimpleDateFormat("HH:00:00")
                    val date = Date()
                    val current_date = sdate.format(date)
                    sd.timeZone = TimeZone.getTimeZone("GMT+05:30")
                    val time = sd.format(date).toString()
                    Log.d("T", time)

                    var flag = false
                    for (i in 0..23) {



                        if (time.equals(res.days[0].hours[i].datetime)) {
                            val status = res.days[0].hours[i].icon
                            val fahrenheit = res.days[0].hours[i].temp
                            val celcius = ((fahrenheit - 32) * 5) / 9
                            when (status) {
                                "partly-cloudy-day" -> {
                                    Log.d("T", "Cloud")
                                    weather_img.setImageResource(R.drawable.cloud)
                                    img_text.text = "Cloud"
                                    flag = true
                                }
                                "clear-day" -> {
                                    Log.d("T", "Clear")
                                    img_text.text = "Clear"
                                    weather_img.setImageResource(R.drawable.clear)
                                    flag = true
                                }
                                "rain" -> {
                                    Log.d("T", "Rainy")
                                    weather_img.setImageResource(R.drawable.rainy)
                                    img_text.text = "Rainy"
                                    flag = true
                                }
                            }
                            tempId.text =
                                ("Temperature:" + " " + celcius.toFloat().toString() + "°C")
                        }
                        if (flag == true) {
                            break
                        }
                    }
                    max_tempId.text =
                        ("Maximum Temperature:" + " " + res.days.get(1).tempmax.toString() + "°F")
                    min_tempId.text =
                        ("Minimum Temperature:" + " " + res.days.get(1).tempmin.toString() + "°F")

                }

            }
        })

    }

}
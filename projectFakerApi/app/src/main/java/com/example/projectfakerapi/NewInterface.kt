package com.example.projectfakerapi

import com.example.projectfakerapi.Model.fake
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://fakerapi.it/api/v1/"
//images?_width=380
interface NewInterface {

    @GET("images?")
    fun getData(@Query("images") images:String): Call<fake>
}

object NewInstnace{
    val instance: NewInterface
    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        instance = retrofit.create(NewInterface::class.java)
    }
}
package com.example.retrofitexample.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {


    companion object {

        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://jsonplaceholder.typicode.com"

        fun create(): Api {

            val client = OkHttpClient.Builder().build()

            if(retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }

            return retrofit!!.create(Api::class.java)
        }

    }
}


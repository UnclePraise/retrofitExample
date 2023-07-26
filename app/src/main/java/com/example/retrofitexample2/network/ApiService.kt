package com.example.retrofitexample2.network

import com.example.retrofitexample2.model.requests.LoginRequest
import com.example.retrofitexample2.model.responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers(
        "content-type: application/json",
        "accept: application/json"
    )
    @POST("login")
    suspend fun login(@Body body: LoginRequest): Call<LoginResponse>
}
package com.example.retrofitexample.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {

    @GET("/books")
    fun getAllBooks(): Call<List<BookInput>?>

    @POST("/books")
    fun createBook(body: BookOutput): Call<BookInput>

}



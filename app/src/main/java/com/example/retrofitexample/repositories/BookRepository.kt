package com.example.retrofitexample.repositories

import android.widget.Toast
import com.example.retrofitexample.api.Api
import com.example.retrofitexample.api.BookInput
import com.example.retrofitexample.api.BookOutput
import com.example.retrofitexample.api.RetrofitClient
import com.example.retrofitexample.model.Book
import com.example.retrofitexample.transformers.BookTransformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.lang.Exception


class BookRepository {

    private var apiClient: Api = RetrofitClient.create()

    suspend fun getAllBooks() : Result<List<Book>?> = withContext(Dispatchers.IO) {

        val response =  apiClient.getAllBooks().awaitResponse()

        if(response.isSuccessful)
        {
            val bookInputList = response.body()

            val list = bookInputList?.map { BookTransformer.toBook(it) }

            Result.success(list)

        } else
        {
            Result.failure(Exception())
        }

    }

    suspend fun createBook(name: String, numPages: Int) : Result<Book> = withContext(Dispatchers.IO) {

        val bookOutput = BookOutput(name, numPages)

        val response = apiClient.createBook(bookOutput).awaitResponse()

        if(response.isSuccessful)
        {
            val bookInput = response.body()!!
            val book = BookTransformer.toBook(bookInput)

            // Add to DB

            Result.success(book)

        } else
        {
            Result.failure(Exception())
        }

    }
}
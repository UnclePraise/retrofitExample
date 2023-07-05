package com.example.retrofitexample.api

import java.util.UUID

data class BookInput(
    val id: String,
    val book_title: String?,
    val num_pages: Int
)

package com.example.retrofitexample.model

import java.util.UUID

data class Book(
    val id: UUID,
    val name: String?,
    val numPages: Int
)

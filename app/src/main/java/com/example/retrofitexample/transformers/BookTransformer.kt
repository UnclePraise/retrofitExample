package com.example.retrofitexample.transformers

import com.example.retrofitexample.api.BookInput
import com.example.retrofitexample.model.Book
import java.util.UUID

class BookTransformer {

    companion object {
        fun toBook(bookInput: BookInput): Book {
            return Book(
                UUID.fromString(bookInput.id),
                bookInput.book_title,
                bookInput.num_pages
            )
        }
    }
}
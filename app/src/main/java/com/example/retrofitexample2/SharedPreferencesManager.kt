package com.example.retrofitexample2

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    companion object {
        val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

    val sharedPref: SharedPreferences =
        context.getSharedPreferences("com.example.retrofitexample2", Context.MODE_PRIVATE)

    fun save(key: String, value: String?) {
        sharedPref.edit().putString(key, value).apply()
    }

    fun save(key: String, value: Int) {
        sharedPref.edit().putInt(key, value).apply()
    }

    fun save(key: String, value: Boolean) {
        sharedPref.edit().putBoolean(key, value).apply()
    }

    fun save(key: String, value: Long) {
        sharedPref.edit().putLong(key, value).apply()
    }

}
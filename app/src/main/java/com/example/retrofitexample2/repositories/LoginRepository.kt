package com.example.retrofitexample2.repositories

import android.content.Context
import android.widget.Toast
import com.example.retrofitexample2.SharedPreferencesManager
import com.example.retrofitexample2.SharedPreferencesManager.Companion.ACCESS_TOKEN
import com.example.retrofitexample2.model.requests.LoginRequest
import com.example.retrofitexample2.model.responses.LoginResponse
import com.example.retrofitexample2.network.ApiClient
import org.json.JSONObject
import retrofit2.awaitResponse
import java.security.AccessController.getContext


class LoginRepository(context: Context) {

    private val client = ApiClient.create()
    private val spm = SharedPreferencesManager(context)

    suspend fun login(email: String, password: String) : Result<Boolean> {

        val requestBody = LoginRequest(email, password)
        val response = client.login(requestBody).awaitResponse()

        if (response.isSuccessful) {

            val responseBody = response.body()
            spm.save(ACCESS_TOKEN, responseBody?.access_token)

            return Result.success(response.isSuccessful)

        } else {

            return try {
                val jObjError = JSONObject(response.errorBody()!!.string())
                val errorMessage = jObjError.getJSONObject("error").getString("message")

                Result.failure(Exception(errorMessage))

            } catch (e: Exception) {

                Result.failure(e)
            }
        }
    }
}


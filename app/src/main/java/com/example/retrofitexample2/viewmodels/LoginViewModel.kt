package com.example.retrofitexample2.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample2.repositories.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private lateinit var loginRepository: LoginRepository

    val loginSuccessful = MutableLiveData<Boolean>()

    fun setUp(context: Context) {

        loginRepository = LoginRepository(context)
    }

    fun login(email: String, password: String) {

        viewModelScope.launch {

            val response = loginRepository.login(email, password)
            loginSuccessful.value =  response.getOrNull() ?: false

        }

    }
}
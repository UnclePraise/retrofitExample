package com.example.retrofitexample2.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitexample2.SharedPreferencesManager
import com.example.retrofitexample2.SharedPreferencesManager.Companion.ACCESS_TOKEN
import com.example.retrofitexample2.databinding.ActivityMainBinding
import com.example.retrofitexample2.ui.theme.RetrofitExample2Theme
import com.example.retrofitexample2.viewmodels.LoginViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var  binding: ActivityMainBinding
    private lateinit var spm : SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this@MainActivity)[LoginViewModel::class.java]
        viewModel.setUp(applicationContext)

        spm = SharedPreferencesManager(this@MainActivity)

        if(spm.sharedPref.getString(ACCESS_TOKEN, null).isNullOrEmpty())
        {
            Toast.makeText(this@MainActivity, "NOT LOGGED IN", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@MainActivity, "LOGGED IN ALREADY", Toast.LENGTH_LONG).show()
        }

        binding.buttonLogin.setOnClickListener {

            //Enable loader here
            viewModel.login(
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString()
            )
        }

        viewModel.loginSuccessful.observe(this@MainActivity) {

            if(it == true) {

                //Disable loader here
                Toast.makeText(this@MainActivity, "LOGGED IN! YAY", Toast.LENGTH_LONG).show()

            } else if (it == false) {

                //Disable loader here
                Toast.makeText(this@MainActivity, "LOGIN FAILED! AWWW :(", Toast.LENGTH_LONG).show()
            }

        }

    }

}

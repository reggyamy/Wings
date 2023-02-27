package com.reggya.wings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.reggya.wings.data.model.User
import com.reggya.wings.data.remote.ApiResponseType
import com.reggya.wings.databinding.ActivityLoginBinding
import com.reggya.wings.ui.ViewModelFactory
import com.reggya.wings.ui.WingsViewModel
import com.reggya.wings.utils.LoginPreference

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: WingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[WingsViewModel::class.java]
        binding.btLogin.setOnClickListener(this)
    }

    private fun setLogin(username: String, password: String){
        viewModel.login(User(user = username, password = password)).observe(this){
            when(it.type){
                ApiResponseType.SUCCESS -> {
                    LoginPreference(this).setLogin(username)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else -> {}
            }
        }

    }

    override fun onClick(v: View?) {
        val username = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()
        when(v?.id) {
            R.id.bt_login -> {
                when {
                    TextUtils.isEmpty(username) -> binding.username.error = "Username tidak boleh kosong"
                    TextUtils.isEmpty(password) -> binding.password.error = "Password tidak boleh kosong"
                    else ->{
                        setLogin(username, password)
                    }
                }
            }
        }
    }
}
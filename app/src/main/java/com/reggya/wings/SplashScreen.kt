package com.reggya.wings

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.reggya.wings.utils.LoginPreference

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            if(!LoginPreference(this).getUsername().isNullOrEmpty()){
                startActivity(Intent(this,MainActivity::class.java))
            }else startActivity(Intent(this,LoginActivity::class.java))
            finish()
        },1500)
    }
}
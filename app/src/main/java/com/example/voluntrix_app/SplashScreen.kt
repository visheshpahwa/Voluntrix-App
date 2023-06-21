package com.example.voluntrix_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        supportActionBar?.hide()
        val handler= Handler(Looper.getMainLooper())
        handler.postDelayed(
            {
                val intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            ,2000)
    }
}

package com.app.zcoolsupport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler=Handler()
        handler.postDelayed(object:Runnable{
            override fun run() {
                Intent(this@SplashActivity,MainActivity::class.java).also {
                    finish()
                    startActivity(it)
                }
            }

        },3000)
    }
}

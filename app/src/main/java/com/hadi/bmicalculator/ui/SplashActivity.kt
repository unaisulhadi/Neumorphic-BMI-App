package com.hadi.bmicalculator.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.hadi.bmicalculator.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = object : CountDownTimer(1500,1000){
            override fun onFinish() {
                startActivity(Intent(this@SplashActivity,
                    MainActivity::class.java))
                finish()
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }

        timer.start()
    }
}

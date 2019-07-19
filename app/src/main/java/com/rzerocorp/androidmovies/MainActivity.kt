package com.rzerocorp.androidmovies

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            var i = Intent(this@MainActivity, MainDashboardActivity::class.java)
            startActivity(i)
        }, 3000)
    }
}

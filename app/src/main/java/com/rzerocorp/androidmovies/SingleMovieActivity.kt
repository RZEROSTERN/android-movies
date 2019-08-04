package com.rzerocorp.androidmovies

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log

import kotlinx.android.synthetic.main.activity_single_movie.*

class SingleMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Movie Information"
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        var extras: Bundle = intent.extras
        Log.d("MOVIE_ID", extras.getInt("movie_id").toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

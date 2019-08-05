package com.rzerocorp.androidmovies

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rzerocorp.androidmovies.adapters.MoviesAdapter
import com.rzerocorp.androidmovies.models.MovieDetailsItem
import com.rzerocorp.androidmovies.models.responses.GenericResponse
import com.rzerocorp.androidmovies.services.TMDApiService

import kotlinx.android.synthetic.main.activity_single_movie.*
import org.jetbrains.anko.image
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class SingleMovieActivity : AppCompatActivity() {
    lateinit var tmdApiService: TMDApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
        setSupportActionBar(toolbar)

        tmdApiService = TMDApiService.create(this@SingleMovieActivity)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Movie Information"
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        var extras: Bundle = intent.extras

        tmdApiService.singleMovieDetails(extras.getInt("movie_id"), getString(R.string.tmdb_api_key)).enqueue(object: Callback<MovieDetailsItem> {
            override fun onFailure(call: Call<MovieDetailsItem>, t: Throwable) {
                toast("Error al llamar la API")
                Log.e("API ERROR", t.message!!)
            }

            override fun onResponse(call: Call<MovieDetailsItem>, response: Response<MovieDetailsItem>) {
                var res: MovieDetailsItem = response.body()!!
                val formatter = DecimalFormat("#,###")

                var poster = findViewById<ImageView>(R.id.img_movie_poster)
                var overview = findViewById<TextView>(R.id.txt_movie_overview)
                var budget = findViewById<TextView>(R.id.txt_movie_budget)
                var revenue = findViewById<TextView>(R.id.txt_movie_revenue)
                var voteAverage = findViewById<TextView>(R.id.txt_vote_average)

                supportActionBar!!.title = res.title

                overview.text = res.overview
                budget.text = "$ " + formatter.format(res.budget)
                revenue.text = "$ " + formatter.format(res.revenue)
                voteAverage.text = res.vote_average.toString()

                Glide.with(this@SingleMovieActivity)
                    .load(getString(R.string.tmdb_img_main_uri) + res.poster_path)
                    .centerCrop()
                    .into(poster)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

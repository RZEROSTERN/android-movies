package com.rzerocorp.androidmovies.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rzerocorp.androidmovies.R
import com.rzerocorp.androidmovies.adapters.MoviesAdapter
import com.rzerocorp.androidmovies.models.MovieItem
import com.rzerocorp.androidmovies.models.responses.GenericResponse
import com.rzerocorp.androidmovies.services.TMDApiService
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedFragment: Fragment() {
    private lateinit var mAdapter: MoviesAdapter
    private lateinit var recyclerView: RecyclerView
    private var items: ArrayList<MovieItem> = ArrayList()

    private val tmdApiService by lazy {
        TMDApiService.create(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_top_rated, container, false)

        Log.d("Test", "Entered")

        tmdApiService.topRated(context!!.getString(R.string.tmdb_api_key)).enqueue(object: Callback<GenericResponse> {
            override fun onFailure(call: Call<GenericResponse>, t: Throwable) {
                context!!.toast("Error al llamar la API")
                Log.e("API ERROR", t.message!!)
            }

            override fun onResponse(call: Call<GenericResponse>, response: Response<GenericResponse>) {
                var res: GenericResponse = response.body()!!
                items = res.results

                recyclerView = view.findViewById(R.id.rv_top_rated) as RecyclerView
                mAdapter = MoviesAdapter(items)

                val mLayoutManager = LinearLayoutManager(context)
                recyclerView.layoutManager = mLayoutManager
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.adapter = mAdapter
            }
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): TopRatedFragment {
            return TopRatedFragment().apply {
                arguments = Bundle().apply {
                    putString("name", "Top Rated")
                }
            }
        }
    }
}
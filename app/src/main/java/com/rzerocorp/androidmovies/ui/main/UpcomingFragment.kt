package com.rzerocorp.androidmovies.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rzerocorp.androidmovies.R
import com.rzerocorp.androidmovies.models.responses.GenericResponse
import com.rzerocorp.androidmovies.services.TMDApiService
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingFragment: Fragment() {
    private val tmdApiService by lazy {
        TMDApiService.create(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_upcoming, container, false)

        tmdApiService.upcoming(context!!.getString(R.string.tmdb_api_key)).enqueue(object: Callback<GenericResponse> {
            override fun onFailure(call: Call<GenericResponse>, t: Throwable) {
                context!!.toast("Error al llamar la API")
                Log.e("ERR", t.message!!)
            }

            override fun onResponse(call: Call<GenericResponse>, response: Response<GenericResponse>) {
                var res: GenericResponse = response.body()!!
                Log.d("RES", res.page.toString())
            }
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): UpcomingFragment {
            return UpcomingFragment().apply {
                arguments = Bundle().apply {
                    putString("name", "Upcoming")
                }
            }
        }
    }
}
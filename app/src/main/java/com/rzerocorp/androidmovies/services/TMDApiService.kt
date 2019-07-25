package com.rzerocorp.androidmovies.services

import android.content.Context
import android.content.res.Resources
import android.provider.Settings.Global.getString
import com.rzerocorp.androidmovies.R
import com.rzerocorp.androidmovies.models.responses.GenericResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDApiService {
    @GET("popular")
    fun popular(@Query("api_key") apiKey: String,
                @Query("language") language: String = "en-US",
                @Query("page") page: Int = 1): Call<GenericResponse>

    @GET("top_rated")
    fun topRated(@Query("api_key") apiKey: String,
                @Query("language") language: String = "en-US",
                @Query("page") page: Int = 1): Call<GenericResponse>

    @GET("upcoming")
    fun upcoming(@Query("api_key") apiKey: String,
                 @Query("language") language: String = "en-US",
                 @Query("page") page: Int = 1): Call<GenericResponse>

    companion object {
        fun create(context: Context): TMDApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.tmdb_api_main_uri))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(TMDApiService::class.java)
        }
    }
}
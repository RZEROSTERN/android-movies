package com.rzerocorp.androidmovies.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.rzerocorp.androidmovies.R
import com.rzerocorp.androidmovies.models.responses.GenericResponse
import okhttp3.Cache
import okhttp3.OkHttpClient
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
            val cacheSize = (5 * 1024 * 1024).toLong()
            val myCache = Cache(context.cacheDir, cacheSize)

            fun hasNetwork(context: Context): Boolean? {
                var isConnected: Boolean? = false // Initial Value
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                if (activeNetwork != null && activeNetwork.isConnected)
                    isConnected = true
                return isConnected
            }

            val okHttpClient = OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (hasNetwork(context)!!)
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                    else
                        request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.tmdb_api_main_uri))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(TMDApiService::class.java)
        }
    }
}
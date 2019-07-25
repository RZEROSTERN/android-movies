package com.rzerocorp.androidmovies.models.responses

import com.rzerocorp.androidmovies.models.MovieItem

class GenericResponse {
    var page: Int = 0
    var total_results: Int = 0
    var total_pages: Int = 0
    var results: ArrayList<MovieItem> = ArrayList()
}
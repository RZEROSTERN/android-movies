package com.rzerocorp.androidmovies.models

class MovieItem {
    lateinit var poster_path: String
    var adult: Boolean = false
    var overview: String = ""
    var release_date: String = ""
    lateinit var genre_ids: Array<Int>
    var id: Int = 0
    var original_title: String = ""
    var original_language: String = ""
    var title: String = ""
    lateinit var backdrop_path: String
    var popularity: Double = 0.0
    var vote_count: Double = 0.0
    var video: Boolean = false
    var vote_average: Double = 0.0
}
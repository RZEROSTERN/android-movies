package com.rzerocorp.androidmovies.models

class MovieDetailsItem {
    var adult: Boolean = false
    var backgrop_path: String = ""
    var budget: Int = 0
    lateinit var genres: ArrayList<GenreItem>
    var homepage: String = ""
    var id: Int = 0
    var imdb_id: String = ""
    var original_language: String = ""
    var original_title: String = ""
    var overview: String = ""
    var popularity: Double = 0.0
    var poster_path: String = ""
    lateinit var production_companies: ArrayList<ProductionCompanyItem>
    var release_date: String = ""
    var revenue: Double = 0.0
    var runtime: Int = 0
    lateinit var spoken_languages: ArrayList<LanguageItem>
    var status: String = ""
    var tagline: String = ""
    var title: String = ""
    var video: Boolean = false
    var vote_average: Double = 0.0
    var vote_count: Int = 0
}
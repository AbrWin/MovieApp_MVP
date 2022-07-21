package com.abrsoftware.myapplication.view.home

class RecommendationResponce {
    var original_title: String? = null
        get() = field
        set(original_title) {
            field = original_title
        }
    var overview: String? = null
    var release_date: String? = null
    var vote_average: String? = null
    var poster_path: String? = null
}
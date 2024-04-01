package com.example.moviesappcompose.features.movies.domain.mapper

import com.example.moviesappcompose.common.base.Mapper
import com.example.moviesappcompose.data.model.Movies
import com.example.networksdk.LATEST
import com.example.networksdk.POPULAR
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<Movies?, List<Movies.Results>?> {
    override fun fromMap(from: Movies?): List<Movies.Results>? {
        return from?.results?.map {
            Movies.Results(
                id = it?.id,
                original_title = it?.original_title,
                overview = it?.overview,
                poster_path = it?.poster_path,
                vote_average = it?.vote_average
            )
        }
    }

}

fun ApiMapper(key: String): String {
    return when (key) {
        LATEST -> "https://api.themoviedb.org/3/movie/upcoming?api_key=f994296f1aa610c1c468e83ce3fa991b"
        POPULAR -> "https://api.themoviedb.org/3/movie/popular?api_key=f994296f1aa610c1c468e83ce3fa991b"
        else -> "https://api.themoviedb.org/3/movie/top_rated?api_key=f994296f1aa610c1c468e83ce3fa991b"
    }
}
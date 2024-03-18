package com.example.moviesappcompose.features.movies.ui

sealed class MovieNavigationItems(val route: String) {

    object MovieList : MovieNavigationItems("movielist")
    object MovieDetails : MovieNavigationItems("movieDetails")

}

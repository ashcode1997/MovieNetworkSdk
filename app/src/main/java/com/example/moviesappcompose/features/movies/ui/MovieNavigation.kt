package com.example.moviesappcompose.features.movies.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieNavigation(
    viewModel: MovieViewModel
) {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = MovieNavigationItems.MovieList.route
    ) {
        composable(MovieNavigationItems.MovieList.route) {
            MovieScreen(viewModel = viewModel, navHostController = navHostController)
        }
        composable(MovieNavigationItems.MovieDetails.route) {
            MovieDetailScreen(viewModel)
        }
    }

}
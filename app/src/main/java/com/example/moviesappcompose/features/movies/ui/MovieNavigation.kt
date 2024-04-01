package com.example.moviesappcompose.features.movies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.networksdk.LATEST
import com.example.networksdk.POPULAR
import com.example.networksdk.TOP_RATED

@Composable
fun MyTabLayout(navHostController: NavHostController, viewModel: MovieViewModel) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(TOP_RATED, POPULAR, LATEST)

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index })
            }
        }
        when (tabIndex) {
            0 -> MovieScreen(viewModel = viewModel, navHostController = navHostController, TOP_RATED)
            1 -> MovieScreen(viewModel = viewModel, navHostController = navHostController, POPULAR)
            else -> MovieScreen(viewModel = viewModel, navHostController = navHostController, LATEST)
        }
    }
}


@Composable
fun screen(viewModel: MovieViewModel) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController, startDestination = MovieNavigationItems.MovieList.route
    ) {
        composable(MovieNavigationItems.MovieList.route) {
            MyTabLayout(navHostController, viewModel)
        }
        composable(MovieNavigationItems.MovieDetails.route) {
            MovieDetailScreen(viewModel)
        }
    }

}


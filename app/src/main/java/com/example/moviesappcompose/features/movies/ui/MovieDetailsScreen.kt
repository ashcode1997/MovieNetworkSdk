package com.example.moviesappcompose.features.movies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ashcode1997.moviesappcompose.R
import com.example.networksdk.BASE_POSTER_URL

@Composable
fun MovieDetailScreen(
    viewModel: MovieViewModel
) {

    val response = viewModel.movieDetails.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Image(
            painter = rememberImagePainter(
                data = "${BASE_POSTER_URL}${response.poster_path ?: ""}",
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_foreground)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(300.dp)
        )
        Text(text = response.original_title ?: "-")
        Text(text = response.vote_average.toString() ?: "0")
        Text(
            text = response.overview ?: "-"
        )

    }

}
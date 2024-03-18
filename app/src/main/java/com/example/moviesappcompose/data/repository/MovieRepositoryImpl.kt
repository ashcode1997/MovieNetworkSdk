package com.example.moviesappcompose.data.repository

import com.example.moviesappcompose.common.ApiState
import com.example.moviesappcompose.data.model.Movies
import com.example.moviesappcompose.features.movies.domain.repository.MovieRepository
import com.example.networksdk.Http
import com.example.networksdk.JSONObjectListener
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MovieRepositoryImpl @Inject constructor(
) : MovieRepository {
    override suspend fun getMovies(): Flow<ApiState<Movies>> = flow {
        try {
            val jsonString = fetchMoviesJson()
            val movies = Gson().fromJson(jsonString, Movies::class.java)
            emit(ApiState.Success(movies))
        } catch (e: Exception) {

        }
    }

    private suspend fun fetchMoviesJson(): String = suspendCoroutine { continuation ->
        val request = Http.Request(Http.GET)
            .url("https://api.themoviedb.org/3/discover/movie?api_key=f994296f1aa610c1c468e83ce3fa991b")
            .execute(object : JSONObjectListener {
                override fun onResponse(res: JSONObject?) {
                    res?.let {
                        continuation.resume(it.toString())
                    }
                }

                override fun onFailure(e: Exception?) {
                }
            })
    }


}
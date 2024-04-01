package com.example.moviesappcompose.data.repository

import com.example.moviesappcompose.common.ApiState
import com.example.moviesappcompose.data.model.Movies
import com.example.moviesappcompose.features.movies.domain.mapper.ApiMapper
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
    override suspend fun getMovies(key: String): Flow<ApiState<Movies>> = flow {
        try {
            val jsonString = fetchMoviesJson(key)
            val movies = Gson().fromJson(jsonString, Movies::class.java)
            emit(ApiState.Success(movies))
        } catch (e: Exception) {

        }
    }

    private suspend fun fetchMoviesJson(key: String): String = suspendCoroutine { continuation ->
        val request = Http.Request(Http.GET)
            .url(ApiMapper(key))
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
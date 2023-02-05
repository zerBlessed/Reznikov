package com.reznikov.tinkofffinteh.data

import com.reznikov.tinkofffinteh.domain.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers(
        Constants.API_KEY_HEADER,
        Constants.CONTENT_TYPE_HEADER
    )
    @GET(Constants.TOP_ENDPOINT + Constants.TOP_100_POPULAR_FILMS_QUERY_PARAM)
    suspend fun getTopFilms(): Response<TopFilmsResponseBody>

    @Headers(
        Constants.API_KEY_HEADER,
        Constants.CONTENT_TYPE_HEADER
    )
    @GET("api/v2.2/films/{filmId}")
    suspend fun getFilmDetails(@Path("filmId") filmId:Int): Response<FilmResponseBody>
}
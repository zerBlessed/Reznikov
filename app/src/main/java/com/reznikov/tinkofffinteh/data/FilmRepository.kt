package com.reznikov.tinkofffinteh.data

interface FilmRepository {
    suspend fun getTopFilms(): List<TopFilm>
    suspend fun getFilmDetailsById(id:Int): FilmResponseBody
}
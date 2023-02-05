package com.reznikov.tinkofffinteh.data

import android.content.Context
import com.reznikov.tinkofffinteh.domain.InternetConnection
import java.util.*

class FilmRepositoryImplementation(context: Context) : FilmRepository {
    private val interConnect: InternetConnection = InternetConnection(context)
    private val apiSer: ApiService = ApiServiceImplementation.service

    override suspend fun getTopFilms(): List<TopFilm> {
        return if (interConnect.isAvailable()) {
            try {
                val res = apiSer.getTopFilms()

                if (res.isSuccessful) {
                    res.body()?.films ?: LinkedList<TopFilm>()
                } else {
                    LinkedList<TopFilm>()
                }
            } catch (e: java.lang.Exception) {
                LinkedList<TopFilm>()
            }
        } else {
            LinkedList<TopFilm>()
        }
    }

    override suspend fun getFilmDetailsById(id: Int): FilmResponseBody {
        return if (interConnect.isAvailable()) {
            try {
                val res = apiSer.getFilmDetails(id)

                if (res.isSuccessful) {
                    res.body() ?: EmptyFilmResponseBody()
                } else {
                    EmptyFilmResponseBody()
                }
            } catch (e: java.lang.Exception) {
                EmptyFilmResponseBody()
            }
        } else {
            EmptyFilmResponseBody()
        }
    }
}
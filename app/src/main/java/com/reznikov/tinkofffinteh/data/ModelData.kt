package com.reznikov.tinkofffinteh.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "film_card_representations")
data class FilmCardRepresentation(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val genre: String,
    val year: String,
    val posterUrlPreview: String,
    var isFavourite: Boolean
)

open class ShortFilmDetails (
    val filmName: String,
    val filmGenre: String,
    val filmCountries: String,
    val description: String,
    val posterUrl: String
)

data class TopFilm (
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val year: String,
    val filmLength: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val rating: String,
    val ratingVoteCount: Int,
    val posterUrl: String,
    val posterUrlPreview: String,
    val ratingChange: Any
)

data class TopFilmsResponseBody (val pagesCount:Int, val films:List<TopFilm>)

data class Country (val country: String)

data class Genre (val genre: String)

open class FilmResponseBody (
    val kinopoiskId: Int,
    val imdbId: String,
    val nameRu: String,
    val nameEn: String,
    val nameOriginal: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val coverUrl: String,
    val logoUrl: String,
    val reviewsCount: Int,
    val ratingGoodReview: Double,
    val ratingGoodReviewVoteCount: Int,
    val ratingKinopoisk: Double,
    val ratingKinopoiskVoteCount: Int,
    val ratingImdb: Double,
    val ratingImdbVoteCount: Int,
    val ratingFilmCritics: Double,
    val ratingFilmCriticsVoteCount: Int,
    val ratingAwait: Double,
    val ratingAwaitCount: Int,
    val ratingRfCritics: Double,
    val ratingRfCriticsVoteCount: Int,
    val webUrl: String,
    val year: Int,
    val filmLength: Int,
    val slogan: String,
    val description: String,
    val shortDescription: String,
    val editorAnnotation: String,
    val isTicketAvailable: Boolean,
    val productionStatus: String,
    val type: String,
    val ratingMpaa: String,
    val ratingAgeLimits: String,
    val hasImax: Boolean,
    val has3D: Boolean,
    val lastSync: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val startYear: Int,
    val endYear: Int,
    val serial: Boolean,
    val shortFilm: Boolean,
    val completed: Boolean
)

class EmptyFilmResponseBody : FilmResponseBody(
    0,
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    0,
    0.0,
    0,
    0.0,
    0,
    0.0,
    0,
    0.0,
    0,
    0.0,
    0,
    0.0,
    0,
    "",
    0,
    0,
    "",
    "",
    "",
    "",
    false,
    "",
    "",
    "",
    "",
    false,
    false,
    "",
    Collections.emptyList(),
    Collections.emptyList(),
    0,
    0,
    false,
    false,
    false
)

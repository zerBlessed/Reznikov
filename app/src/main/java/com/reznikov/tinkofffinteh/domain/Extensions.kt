package com.reznikov.tinkofffinteh.domain

import com.reznikov.tinkofffinteh.data.*

fun List<Genre>.mapGenresToStringList(): List<String> {
    val genList: MutableList<String> = ArrayList()

    for (genre in this) {
        genList.add(genre.genre)
    }

    return genList
}

fun List<Country>.mapCountriesToStringList(): List<String> {
    val countList: MutableList<String> = ArrayList()

    for (country in this) {
        countList.add(country.country)
    }

    return countList
}

fun FilmResponseBody.mapToShortFilmDetails(): ShortFilmDetails {
    return ShortFilmDetails(
        this.nameRu,
        this.genres.mapGenresToStringList().mapToString(),
        this.countries.mapCountriesToStringList().mapToString(),
        this.description,
        this.posterUrl
    )
}

fun TopFilm.mapToFilmCardRepresentation(): FilmCardRepresentation {
    return FilmCardRepresentation(
        filmId,
        nameRu,
        genres.mapGenresToStringList().mapToString(),
        year,
        posterUrlPreview,
        false
    )
}

fun List<String>.mapToString(): String {
    var res: String = String()

    val count = this.size

    if (count > 0) {
        for (item in 1 until count - 1) {
            res += item
        }
        res += this[this.lastIndex]
    }

    return res
}
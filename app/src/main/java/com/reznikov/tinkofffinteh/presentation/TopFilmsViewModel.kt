package com.reznikov.tinkofffinteh.presentation

import android.content.Context

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.reznikov.tinkofffinteh.data.FilmRepositoryImplementation
import com.reznikov.tinkofffinteh.data.FilmCardRepresentation
import com.reznikov.tinkofffinteh.data.TopFilm
import com.reznikov.tinkofffinteh.domain.mapToFilmCardRepresentation
import com.reznikov.tinkofffinteh.data.FilmRepository
import kotlinx.coroutines.*
import java.util.LinkedList

class TopFilmsViewModel(private val context: Context) : ViewModel() {
    private val filmRep: FilmRepository = FilmRepositoryImplementation(context)
    val filmsList: MutableLiveData<List<TopFilm>> = MutableLiveData()
    val topFilmsCardRep: MutableLiveData<List<FilmCardRepresentation>> = MutableLiveData()

    init {
        filmsList.value = ArrayList()
        getTopFilms()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return TopFilmsViewModel(application.applicationContext) as T
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getTopFilms() {
        GlobalScope.launch(Dispatchers.IO) {
            val topFilms: MutableMap<Int, TopFilm> = HashMap()
            val topFilmsFromNet: List<TopFilm> = filmRep.getTopFilms()

            if (topFilmsFromNet.isEmpty()) {
                topFilmsCardRep.postValue(LinkedList())
            } else {
                for (film in topFilmsFromNet) {
                    topFilms[film.filmId] = film
                }

                val resTopList: MutableList<FilmCardRepresentation> = ArrayList()

                for (entry in topFilms.entries.iterator()) {
                    val card: FilmCardRepresentation = entry.value.mapToFilmCardRepresentation()

                    resTopList.add(card)
                    topFilmsCardRep.postValue(resTopList)
                }
            }
        }
    }
}
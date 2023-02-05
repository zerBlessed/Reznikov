package com.reznikov.tinkofffinteh.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.reznikov.tinkofffinteh.data.*
import com.reznikov.tinkofffinteh.domain.mapToShortFilmDetails
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FilmDetailsViewModel(private val context: Context) : ViewModel() {
    private lateinit var film: FilmResponseBody
    val shortFilmDet: MutableLiveData<ShortFilmDetails> = MutableLiveData()
    private val filmRepos: FilmRepository = FilmRepositoryImplementation(context)

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val applic = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return FilmDetailsViewModel(applic.applicationContext) as T
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun loadFilmData(filmId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val res= filmRepos.getFilmDetailsById(filmId)
            if (res is EmptyFilmResponseBody) {
                shortFilmDet.postValue(EmptyFilmDetails())
            } else {
                shortFilmDet.postValue(res.mapToShortFilmDetails())
            }
        }
    }
}
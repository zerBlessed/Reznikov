package com.reznikov.tinkofffinteh.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.reznikov.tinkofffinteh.data.ShortFilmDetails
import com.reznikov.tinkofffinteh.databinding.FragmentFilmDetailsBinding
import com.squareup.picasso.Picasso

class EmptyFilmDetails: ShortFilmDetails("", "", "", "", "")

class FilmDetailsFragment : Fragment() {
    private val viewModel: FilmDetailsViewModel by viewModels { FilmDetailsViewModel.Factory }
    private var bind: FragmentFilmDetailsBinding? = null
    private val args: FilmDetailsFragmentArgs by navArgs()

    override fun onDestroy() {
        super.onDestroy()
        bind = null
    }

    private fun setViewModelObserver() {
        viewModel.shortFilmDet.observe(viewLifecycleOwner, Observer {
            if (it is EmptyFilmDetails) {
                emptyDetails()
            } else {
                showDetails(it)
            }
        })
    }

    private fun emptyDetails() {
        bind!!.apply {
            filmName.visibility = View.INVISIBLE
            filmPoster.visibility = View.INVISIBLE
            filmDescription.visibility = View.INVISIBLE
            filmCountries.visibility = View.INVISIBLE
            filmGenre.visibility = View.INVISIBLE
            internetConnectionErrorImage.visibility = View.VISIBLE
            internetConnectionErrorImage.visibility = View.VISIBLE
            retryButton.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
            genreLabel.visibility = View.INVISIBLE
            countriesLabel.visibility = View.INVISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        val view: View = bind!!.root

        setViewModelObserver()

        val filmId: Int = args.filmId
        viewModel.loadFilmData(filmId)

        loadDetails()

        bind!!.apply {
            backButton.setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
            retryButton.setOnClickListener {
                loadDetails()
            }
        }

        return view
    }

    private fun loadDetails() {
        bind!!.apply {
            filmName.visibility = View.INVISIBLE
            filmPoster.visibility = View.INVISIBLE
            filmDescription.visibility = View.INVISIBLE
            filmCountries.visibility = View.INVISIBLE
            filmGenre.visibility = View.INVISIBLE
            internetConnectionErrorImage.visibility = View.INVISIBLE
            internetConnectionErrorImage.visibility = View.INVISIBLE
            retryButton.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            genreLabel.visibility = View.INVISIBLE
            countriesLabel.visibility = View.INVISIBLE
        }
    }

    private fun showDetails(filmDetails: ShortFilmDetails) {
        bind!!.apply {
            filmName.apply {
                text = filmDetails.filmName
                visibility = View.VISIBLE
            }

            filmDescription.apply {
                text = filmDetails.description
                visibility = View.VISIBLE
            }

            filmCountries.apply {
                text = filmDetails.filmCountries.replace("[^а-яА-Яa-zA-Z ,]".toRegex(), "")
                visibility = View.VISIBLE
            }

            filmGenre.apply {
                text = filmDetails.filmGenre.replace("[^а-яА-Яa-zA-Z ,]".toRegex(), "")
                visibility = View.VISIBLE
            }

            filmPoster.visibility = View.VISIBLE
            Picasso.get().load(filmDetails.posterUrl).fit().into(bind!!.filmPoster)
            internetConnectionErrorImage.visibility = View.INVISIBLE
            internetConnectionErrorImage.visibility = View.INVISIBLE
            retryButton.visibility = View.INVISIBLE
            progressBar.visibility = View.INVISIBLE
            genreLabel.visibility = View.VISIBLE
            countriesLabel.visibility = View.VISIBLE
        }
    }
}
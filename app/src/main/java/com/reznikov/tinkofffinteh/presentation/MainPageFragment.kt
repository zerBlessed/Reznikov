package com.reznikov.tinkofffinteh.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.reznikov.tinkofffinteh.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {
    private var binding: FragmentMainPageBinding? = null
    private val viewModel: TopFilmsViewModel by viewModels { TopFilmsViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

        viewModel.apply {
            topFilmsCardRep.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    emptyPopular()
                } else {
                    val filmsRecyclerViewAdapter = FilmsRecyclerViewAdapter(it)
                    binding?.filmsRecyclerView?.adapter = filmsRecyclerViewAdapter
                    showPopular()
                }
            }
        }

        return view
    }

    private fun emptyPopular() {
        binding!!.apply {
            progressBar.visibility = View.INVISIBLE
            internetConnectionErrorImage.visibility = View.VISIBLE
            internetConnectionErrorMessage.visibility = View.VISIBLE
            retryButton.visibility = View.VISIBLE
            popularButton.visibility = View.INVISIBLE
            favouritesButton.visibility = View.INVISIBLE
            pageHeader.text = "Популярное"
            filmsRecyclerView.visibility = View.INVISIBLE
            retryButton.setOnClickListener {
                requestFilms()
            }
        }
    }

    private fun requestFilms() {
        binding!!.apply {
            internetConnectionErrorImage.visibility = View.INVISIBLE
            internetConnectionErrorMessage.visibility = View.INVISIBLE
            retryButton.visibility = View.INVISIBLE
            popularButton.visibility = View.INVISIBLE
            noFavouriteMessage.visibility = View.INVISIBLE
            favouritesButton.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            viewModel.getTopFilms()
        }
    }

    private fun showPopular() {
        binding?.apply {
            pageHeader.text = "Популярное"
            progressBar.visibility = View.INVISIBLE
            noFavouriteMessage.visibility = View.INVISIBLE
            internetConnectionErrorImage.visibility = View.INVISIBLE
            internetConnectionErrorMessage.visibility = View.INVISIBLE
            filmsRecyclerView.visibility = View.VISIBLE
            popularButton.visibility = View.VISIBLE
            favouritesButton.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
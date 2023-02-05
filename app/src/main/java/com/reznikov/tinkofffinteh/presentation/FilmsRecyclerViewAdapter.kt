package com.reznikov.tinkofffinteh.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.reznikov.tinkofffinteh.data.FilmCardRepresentation
import com.reznikov.tinkofffinteh.databinding.FilmItemBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class FilmsRecyclerViewAdapter(private val filmCardRep: List<FilmCardRepresentation>) :
    RecyclerView.Adapter<FilmsRecyclerViewAdapter.FilmViewHolder>() {

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val currentFilmCardRepresentation: FilmCardRepresentation =  filmCardRep[position]
        holder.bindData(currentFilmCardRepresentation)
    }

    class FilmViewHolder(
        private val binding: FilmItemBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var filmCardRep: FilmCardRepresentation

        @OptIn(DelicateCoroutinesApi::class)
        fun bindData(filmCardRepresentation: FilmCardRepresentation) {
            this.filmCardRep = filmCardRepresentation

            binding.apply {
                filmName.text = filmCardRepresentation.name
                filmGenre.text = filmCardRepresentation.genre.replace("[^а-яА-Яa-zA-Z ,]".toRegex(), "")
                filmYear.text = "(${filmCardRepresentation.year})"

                root.apply {
                    setOnClickListener(View.OnClickListener {
                        val action =
                            MainPageFragmentDirections.actionMainPageFragmentToFilmDetailsFragment(
                                filmCardRepresentation.id
                            )
                        Navigation.findNavController(binding.root).navigate(action)
                    })
                }
            }

            if (filmCardRepresentation.isFavourite) {
                binding.favouriteStar.visibility = View.VISIBLE
            } else {
                binding.favouriteStar.visibility = View.INVISIBLE
            }
            loadFilmPreview()
        }

        private fun loadFilmPreview() {
            Picasso.get().load(filmCardRep.posterUrlPreview).into(binding.filmPoster)
        }
    }

    override fun getItemCount(): Int {
        return filmCardRep.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmItemBinding.inflate(layoutInflater, parent, false)
        return FilmViewHolder(binding, parent.context)
    }
}
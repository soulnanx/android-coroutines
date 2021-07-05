package com.example.androidcoroutines.ui.movie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidcoroutines.databinding.ItemMovieBinding
import com.example.domain.model.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun addMovie(movie: Movie) {
        this.movies.add(movie)
        notifyDataSetChanged()
    }

    fun addAll(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holderMovie: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holderMovie.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.textviewMovieTitle.text = movie.title
            binding.textviewMovieScore.text = movie.score.toString()
            Glide
                .with(binding.root.context)
                .load(movie.url)
                .into(binding.imageviewMovieImage)
        }
    }

}
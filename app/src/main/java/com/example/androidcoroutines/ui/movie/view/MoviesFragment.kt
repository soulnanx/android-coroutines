package com.example.androidcoroutines.ui.movie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidcoroutines.databinding.MoviesFragmentBinding
import com.example.androidcoroutines.ui.movie.viewModel.MoviesViewModel
import com.example.common.infrastructure.Resource
import com.example.domain.model.Movie
import org.koin.android.ext.android.inject


class MoviesFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private val viewModel: MoviesViewModel by inject()

    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding!!

    private val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewMovies.adapter = adapter
        binding.buttonAddMovie.setOnClickListener { viewModel.addMovie() }

        viewModel.movies.observe(
            requireActivity(),
            { result ->
                when(result){
                    is Resource.Success -> setMovies(result.data)
                    is Resource.Error -> showError(result.message)
                    is Resource.Loading -> showLoading()
                }
            }
        )
    }

    private fun setMovies(movies: List<Movie>?) {
        movies?.let { movies ->
            adapter.addAll(movies)
        }
    }

    private fun showError(message: String?) {
        Toast.makeText(context, "callback ${message ?: "erro"}", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        Toast.makeText(context, "carregando", Toast.LENGTH_LONG).show()
    }

}
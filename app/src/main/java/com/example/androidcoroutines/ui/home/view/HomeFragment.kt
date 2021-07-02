package com.example.androidcoroutines.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidcoroutines.databinding.HomeFragmentBinding
import com.example.androidcoroutines.ui.main.MainActivity
import com.example.androidcoroutines.ui.movie.view.MoviesFragment
import com.example.androidcoroutines.ui.tasks.view.TasksFragment

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding : HomeFragmentBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvents()
    }

    private fun setEvents() {
        binding.buttonMovie.setOnClickListener {
            val main = activity as MainActivity
            main.setContainer(MoviesFragment.newInstance())
        }

        binding.buttonTask.setOnClickListener {
            val main = activity as MainActivity
            main.setContainer(TasksFragment.newInstance())
        }
    }


}
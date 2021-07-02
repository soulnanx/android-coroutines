package com.example.androidcoroutines.ui.tasks.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.androidcoroutines.databinding.TasksFragmentBinding
import com.example.androidcoroutines.ui.tasks.viewModel.TasksViewModel
import org.koin.android.ext.android.inject

class TasksFragment : Fragment() {

    companion object {
        fun newInstance() = TasksFragment()
    }

    private val viewModel: TasksViewModel by inject()

    private var _binding: TasksFragmentBinding? = null
    private val binding get() = _binding!!

    private val adapter: TasksAdapter = TasksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TasksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewTasks.adapter = adapter

        viewModel.successGetTasks.observe(
            viewLifecycleOwner,
            Observer { tasks ->
                adapter.addAll(tasks)
            }
        )

    }

}
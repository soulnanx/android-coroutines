package com.example.androidcoroutines.ui.tasks.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcoroutines.databinding.ItemTaskBinding
import com.example.domain.model.Task

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()

    fun addTask(task: Task) {
        this.tasks.add(task)
        notifyDataSetChanged()
    }

    fun addAll(tasks: List<Task>) {
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holderTask: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holderTask.bind(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    class TaskViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.textviewTask.text = task.title
        }
    }

}
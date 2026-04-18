package com.jaimes.helloandroidjaimessebastian.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jaimes.helloandroidjaimessebastian.R
import com.jaimes.helloandroidjaimessebastian.viewmodel.TaskViewModel

class TaskListFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_task_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TaskAdapter(
            onDelete = { task -> viewModel.delete(task) },
            onToggle = { task -> viewModel.update(task) },
            onClick = { task ->
                val action = TaskListFragmentDirections
                    .actionTaskListFragmentToTaskDetailFragment(task.id)
                findNavController().navigate(action)
            }
        )

        view.findViewById<RecyclerView>(R.id.recyclerViewTasks).adapter = adapter

        viewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            adapter.submitList(tasks)
        }

        view.findViewById<FloatingActionButton>(R.id.fabAddTask).setOnClickListener {
            val action = TaskListFragmentDirections
                .actionTaskListFragmentToTaskDetailFragment(-1)
            findNavController().navigate(action)
        }
    }
}
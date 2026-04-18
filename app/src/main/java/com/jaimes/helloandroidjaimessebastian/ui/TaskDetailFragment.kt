package com.jaimes.helloandroidjaimessebastian.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.jaimes.helloandroidjaimessebastian.R
import com.jaimes.helloandroidjaimessebastian.model.Task
import com.jaimes.helloandroidjaimessebastian.viewmodel.TaskViewModel

class TaskDetailFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_task_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etTitle = view.findViewById<TextInputEditText>(R.id.etTaskTitle)
        val etDesc = view.findViewById<TextInputEditText>(R.id.etTaskDescription)

        view.findViewById<Button>(R.id.btnSetReminder).setOnClickListener {
            findNavController().navigate(R.id.action_taskDetailFragment_to_reminderFragment)
        }

        view.findViewById<Button>(R.id.btnSaveTask).setOnClickListener {
            val title = etTitle.text.toString().trim()
            if (title.isEmpty()) {
                Toast.makeText(requireContext(), "El título no puede estar vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val task = Task(
                id = if (args.taskId == -1) 0 else args.taskId,
                title = title,
                description = etDesc.text.toString().trim()
            )
            if (args.taskId == -1) viewModel.insert(task)
            else viewModel.update(task)

            findNavController().popBackStack()
        }
    }
}
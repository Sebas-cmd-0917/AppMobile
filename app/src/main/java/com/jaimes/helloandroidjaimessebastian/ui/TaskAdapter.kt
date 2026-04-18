package com.jaimes.helloandroidjaimessebastian.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaimes.helloandroidjaimessebastian.R
import com.jaimes.helloandroidjaimessebastian.model.Task

class TaskAdapter(
    private val onDelete: (Task) -> Unit,
    private val onToggle: (Task) -> Unit,
    private val onClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var tasks = listOf<Task>()

    fun submitList(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTaskTitle)
        val description: TextView = view.findViewById(R.id.tvTaskDescription)
        val checkbox: CheckBox = view.findViewById(R.id.checkboxCompleted)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDeleteTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.title.text = task.title
        holder.description.text = task.description
        holder.checkbox.isChecked = task.isCompleted

        if (task.isCompleted) {
            holder.title.paintFlags = holder.title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.title.paintFlags = holder.title.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.checkbox.setOnClickListener { onToggle(task.copy(isCompleted = !task.isCompleted)) }
        holder.btnDelete.setOnClickListener { onDelete(task) }
        holder.itemView.setOnClickListener { onClick(task) }
    }

    override fun getItemCount() = tasks.size
}
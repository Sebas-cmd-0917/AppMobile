package com.jaimes.helloandroidjaimessebastian.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jaimes.helloandroidjaimessebastian.model.Task
import com.jaimes.helloandroidjaimessebastian.model.TaskDatabase
import kotlinx.coroutines.launch
import com.jaimes.helloandroidjaimessebastian.repository.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>

    init {
        val dao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(dao)
        allTasks = repository.allTasks
    }

    fun insert(task: Task) = viewModelScope.launch { repository.insert(task) }

    fun update(task: Task) = viewModelScope.launch { repository.update(task) }

    fun delete(task: Task) = viewModelScope.launch { repository.delete(task) }
}
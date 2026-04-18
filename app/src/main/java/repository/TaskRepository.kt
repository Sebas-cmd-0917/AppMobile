package com.jaimes.helloandroidjaimessebastian.repository

import androidx.lifecycle.LiveData
import com.jaimes.helloandroidjaimessebastian.model.Task
import com.jaimes.helloandroidjaimessebastian.model.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) = taskDao.insertTask(task)

    suspend fun update(task: Task) = taskDao.updateTask(task)

    suspend fun delete(task: Task) = taskDao.deleteTask(task)

    suspend fun getById(id: Int) = taskDao.getTaskById(id)
}
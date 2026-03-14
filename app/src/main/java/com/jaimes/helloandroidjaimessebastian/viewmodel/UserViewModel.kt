package com.jaimes.helloandroidjaimessebastian.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaimes.helloandroidjaimessebastian.model.User
import com.jaimes.helloandroidjaimessebastian.repository.UserRepository

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _selectedUser = MutableLiveData<User>()
    val selectedUser: LiveData<User> = _selectedUser

    init {
        loadUsers()
    }

    private fun loadUsers() {
        _users.value = repository.getAllUsers()
    }

    fun addUser(user: User) {
        repository.addUser(user)
        loadUsers()
    }

    fun selectUser(user: User) {
        _selectedUser.value = user
    }

    fun deleteUser(userId: Int) {
        repository.deleteUser(userId)
        loadUsers()
    }
}
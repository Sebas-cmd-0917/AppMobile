package com.jaimes.helloandroidjaimessebastian.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaimes.helloandroidjaimessebastian.model.User
import com.jaimes.helloandroidjaimessebastian.repository.UserRepository

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _selectedUser = MutableLiveData<User?>()
    val selectedUser: LiveData<User?> = _selectedUser

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadUsers()
    }

    fun loadUsers() {
        _isLoading.value = true
        Handler(Looper.getMainLooper()).postDelayed({
            _users.value = repository.getAllUsers()
            _isLoading.value = false
        }, 500)
    }

    fun addUser(name: String, email: String, age: Int) {
        val newId = (repository.getAllUsers().maxOfOrNull { it.id } ?: 0) + 1
        repository.addUser(User(newId, name, email, age))
        loadUsers()
    }

    fun selectUser(user: User) {
        _selectedUser.value = user
    }

    fun getUserById(userId: Int): User? = repository.getUserById(userId)

    fun deleteUser(userId: Int) {
        repository.deleteUser(userId)
        loadUsers()
    }
}

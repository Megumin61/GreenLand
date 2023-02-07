package com.example.jetpacktest02.ViewModel

import androidx.lifecycle.*
import com.example.jetpacktest02.Entity.User
import com.example.jetpacktest02.Repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject  constructor(val repository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData()

    val _uiState = MutableStateFlow(UiState())

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun getUserById(id: Int) = viewModelScope.launch {
        repository.getUserById(id)
    }

    fun getUser(id: Int): User {
        return repository.getUserById(id)
    }
    fun UpdateUser(user: User){
        return repository.updateUser(user)
    }

    suspend fun InsertUser(user: User){
        return repository.insertUser(user)
    }
    fun DeleteUser(id: Int){
        return repository.deleteUserById(id)
    }




}

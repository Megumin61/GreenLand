package com.example.jetpacktest02.ViewModel

import androidx.lifecycle.*
import com.example.jetpacktest02.Entity.User
import com.example.jetpacktest02.Repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

//@HiltViewModel
class UserViewModel() : ViewModel() {
    val counterLiveData: LiveData<Int>
        get() = counter

    private val counter = MutableLiveData<Int>()
    private var count = 0

    fun increaseCounter() {
        counter.value = ++count
    }


}

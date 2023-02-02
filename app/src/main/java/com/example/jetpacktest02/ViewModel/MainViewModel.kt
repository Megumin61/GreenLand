package com.example.jetpacktest02.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val counterLiveData: LiveData<Int>
        get() = counter

    private val counter = MutableLiveData<Int>()
    private var count = 0

    fun increaseCounter() {
        counter.value = ++count
    }
}

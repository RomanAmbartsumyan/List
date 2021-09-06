package com.example.list.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.list.objects.Card

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainFragmentRepository()
    private val mutableLiveData = MutableLiveData<List<Card>>()

    val cardLiveDataList: LiveData<List<Card>>
        get() = mutableLiveData

    fun initList() {
        mutableLiveData.postValue(repository.initList())
    }

    fun onDelete(position: Int) {
        mutableLiveData.postValue(repository.delete(position))
    }

    suspend fun addCard() {
        mutableLiveData.postValue(repository.addCard())
    }
}
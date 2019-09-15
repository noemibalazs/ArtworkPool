package com.example.artworkpool.helper

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

object Status : LiveData<Boolean>(){

    private var status: MutableLiveData<Boolean> = MutableLiveData()

    fun getStatus(): LiveData<Boolean> = status

    fun setStatus(notEmpty: Boolean){
        status.value = notEmpty
    }
}
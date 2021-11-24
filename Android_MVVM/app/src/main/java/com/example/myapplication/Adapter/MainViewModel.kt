package com.example.myapplication.Adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.ItemData

open class MainViewModel: ViewModel() {
    var oldList = MutableLiveData<ArrayList<ItemData>>()
    var newList = arrayListOf<ItemData>()

    fun add(data: ItemData) {
        newList.add(data)
        oldList.value = newList
    }

    fun remove(data: ItemData) {
        newList.remove(data)
        oldList.value = newList
    }
}
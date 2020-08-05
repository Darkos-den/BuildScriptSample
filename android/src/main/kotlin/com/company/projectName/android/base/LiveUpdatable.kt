package com.company.projectName.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LiveUpdatable<T : Any>(
    initialState: T
) {
    private var value: T = initialState
        set(value) {
            field = value
            liveSource.postValue(value)
        }
    private val liveSource = MutableLiveData<T>()
    val liveValue: LiveData<T> = liveSource

    fun set(newValue: T) {
        value = newValue
    }
}
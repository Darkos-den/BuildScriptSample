package com.company.projectName.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.projectName.android.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModel : IContext {

    private var currentState: State = Initial(this)
        set(value) {
            field = value
            viewStateSource.postValue(value)
        }
    private val viewStateSource = MutableLiveData<State>()
    val viewState: LiveData<State> = viewStateSource

    init {
        invalidateData()
    }

    private fun invalidateData() {
        CoroutineScope(Dispatchers.IO).launch {
            Progress(this@ViewModel, currentState.coreState).let {
                currentState = it
            }

            delay(4000)//todo: for test

            Data(this@ViewModel, "data data data data").let {
                Invalidatable(this@ViewModel, it)
            }.let {
                currentState = it
            }
        }
    }

    override fun onInvalidateClick() {
        invalidateData()
    }
}
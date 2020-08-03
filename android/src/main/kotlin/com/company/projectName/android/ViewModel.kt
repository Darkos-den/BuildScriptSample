package com.company.projectName.android

import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.projectName.android.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

typealias State = @Composable() () -> Unit

class ViewModel {

    private var currentState: State = { Initial() }
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
            val oldState = currentState
            currentState = { Progress { oldState() } }

            delay(4000)//todo: for test

            val dataArg = "data data data data"
            currentState = { Invalidatable({ Data(dataArg) }, this@ViewModel::invalidateData) }
        }
    }
}
package com.company.projectName.android.clean.presentation

import android.content.Context
import androidx.compose.Composable
import androidx.lifecycle.MutableLiveData
import com.company.projectName.android.base.mvu.Component
import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.core.Program

@ExperimentalStdlibApi
abstract class BaseComponent<T : ScreenState>(
    protected val parentContext: Context,
    protected val messageQuery: MessageQuery
) : Component {

    val uiState = MutableLiveData<T>()

    init {
        run()
    }

    abstract fun injectProgram(): Program

    private fun run() {
        injectProgram().start()
    }

    override fun render(state: ScreenState) {
        uiState.postValue(mapToUiModel(state))
    }

    open fun mapToUiModel(state: ScreenState): T = state as T

    @Composable
    abstract fun drawState()
}
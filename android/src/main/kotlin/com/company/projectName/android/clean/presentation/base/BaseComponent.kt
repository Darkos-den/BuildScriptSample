package com.company.projectName.android.clean.presentation.base

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
    lateinit var program: Program

    init {
        run()
    }

    abstract fun injectProgram(): Program

    private fun run() {
        program = injectProgram()
        program.start()
    }

    override fun render(state: ScreenState) {
        uiState.postValue(mapToUiModel(state))
    }

    open fun mapToUiModel(state: ScreenState): T = state as T

    @Composable
    abstract fun drawState()

    fun clear() {
        program.clear()
    }
}
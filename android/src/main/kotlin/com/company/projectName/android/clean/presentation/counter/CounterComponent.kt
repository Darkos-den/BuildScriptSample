package com.company.projectName.android.clean.presentation.counter

import android.content.Context
import android.util.Log
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.onDispose
import androidx.ui.livedata.observeAsState
import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.di.CounterDI
import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.core.Program
import com.company.projectName.android.clean.domain.feature.counter.CounterState
import com.company.projectName.android.clean.domain.feature.counter.contract.CounterContract
import com.company.projectName.android.clean.presentation.base.BaseComponent

@ExperimentalStdlibApi
class CounterComponent(
    parentContext: Context,
    messageQuery: MessageQuery
) : BaseComponent<CounterUiState>(
    parentContext,
    messageQuery
) {

    override fun injectProgram(): Program {
        return CounterDI(messageQuery, this).createProgram()
    }

    override fun mapToUiModel(state: ScreenState): CounterUiState {
        return (state as CounterState).toUiState()
    }

    @Composable
    override fun drawState() {
        val state by uiState.observeAsState()

        onDispose {
            Log.d("myLog", "dispose screen")
            //todo: send dispose message
        }

        Counter(
            state = state ?: return,
            timerClick = {
                messageQuery.accept(CounterContract.Message.TimerClick)
            }
        )
    }
}
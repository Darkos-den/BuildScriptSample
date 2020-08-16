package com.company.projectName.android.clean.presentation

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
import com.company.projectName.android.clean.domain.feature.counter.CounterContract
import com.company.projectName.android.clean.domain.feature.counter.CounterState

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
        return CounterUiState.fromDomain(state as CounterState)
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
                if (state?.isProgress == true) {
                    CounterContract.Message.StopTimerClick
                } else {
                    CounterContract.Message.StartTimerClick
                }.let {
                    messageQuery.accept(it)
                }
            }
        )
    }
}
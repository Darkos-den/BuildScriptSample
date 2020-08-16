package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.base.mvu.*
import com.company.projectName.android.clean.domain.ServiceContract

class CounterReducer : Reducer {

    override fun update(state: ScreenState, msg: Msg): ScreenCmdData {
        state as CounterState
        return when (msg) {
            is CounterContract.Message.StartTimerClick -> {
                ScreenCmdData(
                    state = state.copy(isProgress = true),
                    cmd = CounterContract.Command.StartTimer
                )
            }
            is CounterContract.Message.StopTimerClick -> {
                ScreenCmdData(
                    state = state.copy(isProgress = false),
                    cmd = CounterContract.Command.StopTimer
                )
            }
            is ServiceContract.Message.CounterNewValue -> {
                ScreenCmdData(
                    state = state.copy(counter = msg.value),
                    cmd = None()
                )
            }
            else -> {
                ScreenCmdData(
                    state = state,
                    cmd = None()
                )
            }
        }
    }
}
package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.base.mvu.None
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.base.mvu.reducer
import com.company.projectName.android.clean.domain.feature.counter.contract.CounterContract
import com.company.projectName.android.clean.domain.feature.counter.contract.TimerContract

val counterReducer = reducer { state, msg ->
    state as CounterState
    when (msg) {
        is CounterContract.Message.TimerClick -> {
            when (state) {
                is CounterState.Active -> {
                    ScreenCmdData(
                        state = CounterState.NotActive(state.counter),
                        cmd = CounterContract.Command.StopTimer
                    )
                }
                is CounterState.NotActive -> {
                    ScreenCmdData(
                        state = CounterState.Active(state.counter),
                        cmd = CounterContract.Command.StartTimer
                    )
                }
            }
        }
        is TimerContract.Message.CounterNewValue -> {
            when (state) {
                is CounterState.Active -> state.copy(counter = msg.value)
                is CounterState.NotActive -> state.copy(counter = msg.value)
            }.let {
                ScreenCmdData(
                    state = it,
                    cmd = None()
                )
            }
        }
        else -> {
            ScreenCmdData(
                state = state,
                cmd = None()
            )
        }
    }
}
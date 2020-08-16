package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.base.mvu.None
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.base.mvu.reducer
import com.company.projectName.android.clean.domain.feature.counter.contract.CounterContract

val counterReducer = reducer { state, msg ->
    state as CounterState
    when (msg) {
        is CounterContract.Message.TimerClick -> {
            state.timerClick()
        }
        is CounterContract.Message.CounterNewValue -> {
            state.newCounterValue(msg.value)
        }
        else -> {
            ScreenCmdData(
                state = state,
                cmd = None()
            )
        }
    }
}
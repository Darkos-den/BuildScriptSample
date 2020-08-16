package com.company.projectName.android.clean.domain.feature.namedCounter

import com.company.projectName.android.base.mvu.None
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.base.mvu.reducer
import com.company.projectName.android.clean.domain.feature.counter.CounterState
import com.company.projectName.android.clean.domain.feature.counter.contract.CounterContract
import com.company.projectName.android.clean.domain.feature.counter.contract.TimerContract
import com.company.projectName.android.clean.domain.feature.counter.counterReducer

val namedCounterReducer = reducer { state, msg ->
    state as NamedCounterState
    when (msg) {
        is TimerContract.Message,
        is CounterContract.Message -> counterReducer.update(state.counter, msg).let {
            ScreenCmdData(
                state = state.copy(counter = it.state as CounterState),
                cmd = it.cmd
            )
        }
        is NamedCounterContract.Message.NameChanged -> {
            ScreenCmdData(
                state = state.copy(name = msg.value),
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
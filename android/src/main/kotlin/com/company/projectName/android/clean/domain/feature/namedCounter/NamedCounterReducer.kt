package com.company.projectName.android.clean.domain.feature.namedCounter

import com.company.projectName.android.base.mvu.None
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.base.mvu.reducer
import com.company.projectName.android.clean.domain.feature.counter.CounterState
import com.company.projectName.android.clean.domain.feature.counter.contract.CounterContract
import com.company.projectName.android.clean.domain.feature.counter.counterReducer
import com.company.projectName.android.clean.domain.feature.hintedTextField.HintedTextFieldContract
import com.company.projectName.android.clean.domain.feature.hintedTextField.HintedTextFiledState
import com.company.projectName.android.clean.domain.feature.hintedTextField.hintedTextFieldReducer

val namedCounterReducer = reducer { state, msg ->
    state as NamedCounterState
    when (msg) {
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
        is HintedTextFieldContract.Message.FocusChanged,
        is HintedTextFieldContract.Message.TextChanged -> hintedTextFieldReducer.update(
            state.textField,
            msg
        ).let {
            ScreenCmdData(
                state = state.copy(textField = it.state as HintedTextFiledState),
                cmd = it.cmd
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
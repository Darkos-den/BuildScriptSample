package com.company.projectName.android.clean.domain.feature.hintedTextField

import com.company.projectName.android.base.mvu.None
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.base.mvu.reducer

val hintedTextFieldReducer = reducer { state, msg ->
    state as HintedTextFiledState

    return@reducer when (msg) {
        is HintedTextFieldContract.Message.FocusChanged -> {
            if (msg.focus) {
                if (state is HintedTextFiledState.HintDisplay) {
                    HintedTextFiledState.TextDisplay(
                        text = "",
                        hint = state.hint
                    )
                } else {
                    state
                }
            } else {
                if (state is HintedTextFiledState.TextDisplay) {
                    if (state.text.isEmpty()) {
                        HintedTextFiledState.HintDisplay(
                            hint = state.hint
                        )
                    } else {
                        state
                    }
                } else {
                    throw IllegalStateException("как ты сука это сообщение из этого состояния вызвал???")
                }
            }
        }
        is HintedTextFieldContract.Message.TextChanged -> {
            if (state is HintedTextFiledState.TextDisplay) {
                state.copy(text = msg.text)
            } else {
                throw IllegalStateException("как ты сука это сообщение из этого состояния вызвал???")
            }
        }
        else -> state
    }.let {
        ScreenCmdData(
            state = it,
            cmd = None()
        )
    }
}
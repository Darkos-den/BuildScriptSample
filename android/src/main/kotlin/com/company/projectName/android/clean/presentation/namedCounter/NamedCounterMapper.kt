package com.company.projectName.android.clean.presentation.namedCounter

import com.company.projectName.android.clean.domain.feature.hintedTextField.HintedTextFiledState
import com.company.projectName.android.clean.domain.feature.namedCounter.NamedCounterState
import com.company.projectName.android.clean.presentation.counter.toUiState
import com.darkos.mylibrary.presentation.TextFiledUiState

fun namedCounterStateToUiState(model: NamedCounterState): NamedUiState {
    return NamedUiState(
        name = model.name,
        counter = model.counter.toUiState(),
        textFiledState = model.textField.toUiState()
    )
}

fun NamedCounterState.toUiState(): NamedUiState {
    return namedCounterStateToUiState(this)
}

fun HintedTextFiledState.toUiState(): TextFiledUiState {
    return when (this) {
        is HintedTextFiledState.HintDisplay -> TextFiledUiState.HintDisplay(hint)
        is HintedTextFiledState.TextDisplay -> TextFiledUiState.TextDisplay(
            text = text,
            hint = hint
        )
    }
}
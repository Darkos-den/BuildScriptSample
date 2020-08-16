package com.company.projectName.android.clean.presentation.counter

import com.company.projectName.android.clean.domain.feature.counter.CounterState

fun counterStateToUiState(model: CounterState): CounterUiState {
    return CounterUiState(
        buttonText = if (model is CounterState.Active) {
            "stop timer"
        } else {
            "start timer"
        },
        counter = model.counter * 10
    )
}

fun CounterState.toUiState(): CounterUiState {
    return counterStateToUiState(this)
}
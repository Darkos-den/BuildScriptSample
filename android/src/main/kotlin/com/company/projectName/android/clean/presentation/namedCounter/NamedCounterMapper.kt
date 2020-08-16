package com.company.projectName.android.clean.presentation.namedCounter

import com.company.projectName.android.clean.domain.feature.namedCounter.NamedCounterState
import com.company.projectName.android.clean.presentation.counter.toUiState

fun namedCounterStateToUiState(model: NamedCounterState): NamedUiState {
    return NamedUiState(
        name = model.name,
        counter = model.counter.toUiState()
    )
}

fun NamedCounterState.toUiState(): NamedUiState {
    return namedCounterStateToUiState(this)
}
package com.company.projectName.android.clean.presentation

import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.domain.feature.counter.CounterState

data class CounterUiState(
    val isProgress: Boolean,
    val counter: Int
) : ScreenState() {

    companion object {
        fun fromDomain(model: CounterState): CounterUiState {
            return CounterUiState(
                isProgress = model.isProgress,
                counter = model.counter * 10
            )
        }
    }
}
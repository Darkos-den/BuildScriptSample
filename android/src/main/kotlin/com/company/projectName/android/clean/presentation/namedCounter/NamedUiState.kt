package com.company.projectName.android.clean.presentation.namedCounter

import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.domain.feature.namedCounter.NamedCounterState
import com.company.projectName.android.clean.presentation.counter.CounterUiState
import com.company.projectName.android.clean.presentation.counter.toUiState

data class NamedUiState(
    val name: String,
    val counter: CounterUiState
) : ScreenState()
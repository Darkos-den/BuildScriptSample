package com.company.projectName.android.clean.presentation.namedCounter

import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.presentation.counter.CounterUiState
import com.darkos.mylibrary.presentation.TextFiledUiState

data class NamedUiState(
    val name: String,
    val counter: CounterUiState,
    val textFiledState: TextFiledUiState
) : ScreenState()
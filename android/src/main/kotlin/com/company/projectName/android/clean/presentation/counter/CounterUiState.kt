package com.company.projectName.android.clean.presentation.counter

import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.domain.feature.counter.CounterState

data class CounterUiState(
    val buttonText: String,
    val counter: Int
) : ScreenState()
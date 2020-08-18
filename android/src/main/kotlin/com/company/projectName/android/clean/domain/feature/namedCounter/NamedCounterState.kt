package com.company.projectName.android.clean.domain.feature.namedCounter

import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.domain.feature.counter.CounterState
import com.company.projectName.android.clean.domain.feature.hintedTextField.HintedTextFiledState

data class NamedCounterState(
    val name: String,
    val counter: CounterState,
    val textField: HintedTextFiledState
): ScreenState()
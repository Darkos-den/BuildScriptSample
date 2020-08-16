package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.base.mvu.ScreenState

data class CounterState(
    val isProgress: Boolean,
    val counter: Int
): ScreenState()
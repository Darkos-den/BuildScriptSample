package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.base.mvu.ScreenState

sealed class CounterState : ScreenState() {
    open val counter: Int = 0

    data class Active(
        override val counter: Int
    ) : CounterState()

    data class NotActive(
        override val counter: Int
    ) : CounterState()
}
package com.company.projectName.android.clean.domain.feature.counter.contract

import com.company.projectName.android.base.mvu.Msg

sealed class TimerContract {

    sealed class Message : Msg() {
        class CounterNewValue(val value: Int): Message()
    }
}
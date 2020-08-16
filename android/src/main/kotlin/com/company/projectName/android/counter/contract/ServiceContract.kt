package com.company.projectName.android.counter.contract

import com.company.projectName.android.base.mvu.Msg

sealed class ServiceContract {

    sealed class Message : Msg() {
        class CounterNewValue(val value: Int): Message()
    }
}
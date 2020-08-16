package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.base.mvu.Cmd
import com.company.projectName.android.base.mvu.Msg

sealed class CounterContract {
    sealed class Message: Msg() {
        object StartTimerClick: Message()
        object StopTimerClick: Message()
    }
    sealed class Command: Cmd(){
        object StartTimer: Command()
        object StopTimer: Command()
    }
}
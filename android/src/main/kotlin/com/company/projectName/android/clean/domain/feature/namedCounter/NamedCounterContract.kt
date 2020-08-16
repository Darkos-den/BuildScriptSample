package com.company.projectName.android.clean.domain.feature.namedCounter

import com.company.projectName.android.base.mvu.Msg

sealed class NamedCounterContract {
    sealed class Message : Msg() {
        class NameChanged(val value: String): Message()
    }
}
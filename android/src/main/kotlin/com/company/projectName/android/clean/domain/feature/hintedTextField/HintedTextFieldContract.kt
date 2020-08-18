package com.company.projectName.android.clean.domain.feature.hintedTextField

import com.company.projectName.android.base.mvu.Msg

sealed class HintedTextFieldContract {
    sealed class Message : Msg() {
        class FocusChanged(
            val focus: Boolean
        ) : Message()

        class TextChanged(
            val text: String
        ) : Message()
    }
}
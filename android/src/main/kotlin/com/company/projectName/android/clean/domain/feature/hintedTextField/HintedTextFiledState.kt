package com.company.projectName.android.clean.domain.feature.hintedTextField

import com.company.projectName.android.base.mvu.ScreenState

sealed class HintedTextFiledState : ScreenState() {
    abstract val hint: String

    data class HintDisplay(
        override val hint: String
    ) : HintedTextFiledState()

    data class TextDisplay(
        val text: String,
        override val hint: String
    ) : HintedTextFiledState()
}
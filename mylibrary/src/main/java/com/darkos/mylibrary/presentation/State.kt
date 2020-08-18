package com.darkos.mylibrary.presentation

sealed class TextFiledUiState {
    abstract val hint: String

    data class HintDisplay(
        override val hint: String
    ) : TextFiledUiState()

    data class TextDisplay(
        val text: String,
        override val hint: String
    ) : TextFiledUiState()
}
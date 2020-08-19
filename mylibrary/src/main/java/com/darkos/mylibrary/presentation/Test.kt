package com.darkos.mylibrary.presentation

import androidx.compose.Composable

@Composable
fun TestComponent(
    hint: String,
    text: String,
    onTextChanged: (String) -> Unit
) {


    TextFiledWithHint(
        state = TextFiledUiState.TextDisplay("", ""),
        onFocusChanged = {},
        onTextChanged = {}
    )
}
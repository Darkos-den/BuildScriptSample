package com.darkos.mylibrary.presentation

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.foundation.TextField
import androidx.ui.foundation.TextFieldValue
import androidx.ui.text.TextRange

@Composable
fun TextFiledWithHint(
    state: TextFiledUiState,
    onFocusChanged: (Boolean) -> Unit,
    onTextChanged: (String) -> Unit
) {
    when (state) {
        is TextFiledUiState.HintDisplay -> {
            EmptyWidget(
                hint = state.hint,
                onFocusChanged = onFocusChanged
            )
        }
        is TextFiledUiState.TextDisplay -> {
            NotEmptyWidget(
                text = state.text,
                onFocusChanged = onFocusChanged,
                onTextChanged = onTextChanged
            )
        }
    }
}

@Composable
internal fun EmptyWidget(
    hint: String,
    onFocusChanged: (Boolean) -> Unit
) {
    TextField(
        value = TextFieldValue(
            text = hint
        ),
        onValueChange = {},
        onFocusChange = {
            if (it.not()) {
                return@TextField
            }
            onFocusChanged(it)
        }
    )
}

@Composable
internal fun NotEmptyWidget(
    text: String,
    onFocusChanged: (Boolean) -> Unit,
    onTextChanged: (String) -> Unit
) {
    var range by state { TextRange(0, 0) }

    TextField(
        value = TextFieldValue(
            text = text,
            selection = range
        ),
        onValueChange = {
            range = it.selection
            if (it.text != text) {
                onTextChanged(it.text)
            }
        },
        onFocusChange = {
            if (it) {
                return@TextField
            }
            onFocusChanged(it)
        }
    )
}
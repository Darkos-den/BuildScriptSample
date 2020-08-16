package com.company.projectName.android.clean.presentation.namedCounter

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.foundation.TextField
import androidx.ui.foundation.TextFieldValue
import androidx.ui.layout.Column
import androidx.ui.text.TextRange
import com.company.projectName.android.clean.presentation.counter.Counter

@Composable
fun NamedCounter(
    timerClick: () -> Unit,
    nameChanged: (String) -> Unit,
    uiState: NamedUiState
) {
    Column {
        var range by state { TextRange(0, 0) }

        TextField(
            value = TextFieldValue(
                text = uiState.name,
                selection = range
            ),
            onValueChange = {
                range = it.selection
                if (it.text != uiState.name) {
                    nameChanged(it.text)
                }
            }
        )
        Counter(timerClick = timerClick, state = uiState.counter)
    }
}
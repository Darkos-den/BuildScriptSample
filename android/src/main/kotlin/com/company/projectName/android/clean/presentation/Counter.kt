package com.company.projectName.android.clean.presentation

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.Button

@Composable
fun Counter(
    timerClick: () -> Unit,
    state: CounterUiState
) {
    Column {
        Text(text = "counter: ${state.counter}")
        Button(onClick = timerClick) {
            val text = if (state.isProgress) {
                "stop timer"
            } else {
                "start timer"
            }
            Text(text = text)
        }
    }
}
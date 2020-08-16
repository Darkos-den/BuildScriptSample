package com.company.projectName.android.clean.presentation.counter

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
            Text(text = state.buttonText)
        }
    }
}
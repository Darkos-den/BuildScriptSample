package com.company.projectName.android.home.view

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.material.Button
import androidx.ui.material.CircularProgressIndicator

@Composable
fun Invalidatable(
    oldState: @Composable() () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Button(
            text = {
                Text(text = "Invalidate")
            },
            onClick = {
                onClick()
            }
        )
        oldState()
    }
}
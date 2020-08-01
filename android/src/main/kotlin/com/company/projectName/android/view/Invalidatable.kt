package com.company.projectName.android.view

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

class Invalidatable(
    context: IContext,
    private val oldState: State
) : State(context) {

    override val coreState = oldState

    @Composable
    override fun draw() {
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
                    context.onInvalidateClick()
                }
            )
            oldState.draw()
        }
    }
}
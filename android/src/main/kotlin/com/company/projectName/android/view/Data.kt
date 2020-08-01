package com.company.projectName.android.view

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.unit.dp

class Data(
    context: IContext,
    private val data: String
): State(context) {

    override val coreState = this

    @Composable
    override fun draw() {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            text = data
        )
    }
}
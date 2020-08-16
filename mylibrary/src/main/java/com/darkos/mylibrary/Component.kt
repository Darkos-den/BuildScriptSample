package com.darkos.mylibrary

import androidx.compose.Composable
import androidx.compose.onCommit

data class ComponentData(
    val enabled: Boolean,
    internal val isChecked: Boolean
)

@Composable
fun Component(){
    onCommit{

    }


}
package com.company.projectName.android.component

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state

data class ComponentData(
    val enabled: Boolean,
    internal val isChecked: Boolean
) {
    companion object {
        fun initial() = ComponentData(
            enabled = true,
            isChecked = false
        )
    }
}

@ExperimentalStdlibApi
@Composable
fun Component() {
    var componentData by state {
        ComponentData.initial()
    }


}

@Composable
fun initial() {

}
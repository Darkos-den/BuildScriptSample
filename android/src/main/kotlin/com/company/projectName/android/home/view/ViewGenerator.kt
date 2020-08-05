package com.company.projectName.android.home.view

import androidx.compose.Composable
import com.company.projectName.android.home.HomeScreenState

class ViewGenerator(
    private val onInvalidate: () -> Unit
) {

    @Composable
    fun generateState(state: HomeScreenState) {
        when (state) {
            is HomeScreenState.Initial -> {
                Initial()
            }
            is HomeScreenState.Invalidatable -> {
                Invalidatable(
                    oldState = {
                        generateState(state.oldState as HomeScreenState)
                    }, onClick = onInvalidate
                )
            }
            is HomeScreenState.Data -> {
                Data(data = state.data)
            }
            is HomeScreenState.Progress -> {
                Progress {
                    generateState(state.oldState as HomeScreenState)
                }
            }
        }
    }
}
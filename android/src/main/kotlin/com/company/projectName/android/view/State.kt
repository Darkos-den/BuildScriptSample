package com.company.projectName.android.view

import androidx.compose.Composable

abstract class State(
    protected val context: IContext
){

    abstract val coreState: State

    @Composable
    abstract fun draw()
}
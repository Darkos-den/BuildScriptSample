package com.company.projectName.android.base

import androidx.compose.Composable

interface Component {
    fun update(state: ScreenState, msg: Msg): ScreenCmdData
    fun render(state: ScreenState)
    suspend fun call(cmd: Cmd): Msg
}
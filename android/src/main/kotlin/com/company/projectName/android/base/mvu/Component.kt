package com.company.projectName.android.base.mvu

interface Component {
    fun update(state: ScreenState, msg: Msg): ScreenCmdData //reducer
    fun render(state: ScreenState)
    suspend fun call(cmd: Cmd): Msg // effect handler
}
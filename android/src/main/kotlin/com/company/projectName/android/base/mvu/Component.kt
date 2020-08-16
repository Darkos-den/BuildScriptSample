package com.company.projectName.android.base.mvu

interface Component {
    fun render(state: ScreenState)
}

interface Reducer {
    fun update(state: ScreenState, msg: Msg): ScreenCmdData
}

interface EffectHandler {
    suspend fun call(cmd: Cmd): Msg
}
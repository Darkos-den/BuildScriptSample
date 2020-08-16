package com.company.projectName.android.base.mvu

interface Component {
    fun render(state: ScreenState)
}

fun component(block: (ScreenState) -> Unit) = object : Component {
    override fun render(state: ScreenState) {
        block(state)
    }
}

interface Reducer {
    fun update(state: ScreenState, msg: Msg): ScreenCmdData
}

interface EffectHandler {
    suspend fun call(cmd: Cmd): Msg
}
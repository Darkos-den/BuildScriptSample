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

fun reducer(block: (state: ScreenState, msg: Msg) -> ScreenCmdData) = object : Reducer {
    override fun update(state: ScreenState, msg: Msg): ScreenCmdData {
        return block(state, msg)
    }
}

interface EffectHandler {
    suspend fun call(cmd: Cmd): Msg
}
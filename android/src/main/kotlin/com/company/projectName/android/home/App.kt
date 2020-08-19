package com.company.projectName.android.home

import android.app.Application
import com.company.projectName.android.base.mvu.*
import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.core.Program

class App : Application() {

    @ExperimentalStdlibApi
    val messageQuery = MessageQuery()

    /**
     * работает на протяжении жизни приложения
     */
    @ExperimentalStdlibApi
    val program = Program(
        reducer = reducer { state, msg -> ScreenCmdData(state, None()) },
        messageQuery = messageQuery,
        effectHandler = object : EffectHandler {
            override suspend fun call(cmd: Cmd): Msg {
                return Idle()
            }
        },
        component = component { },
        initialState = object : ScreenState() {}
    )

    override fun onCreate() {
        super.onCreate()
    }
}
package com.company.projectName.android.counter

import com.company.projectName.android.counter.service.Service
import com.company.projectName.android.home.Program

@ExperimentalStdlibApi
class CounterDI {

    val messageQuery = MessageQuery()

    fun createProgram(): Program {
        return Program(
            reducer = CounterComponent.DefaultReducer(),
            messageQuery = messageQuery,
            effectHandler = CounterComponent.DefaultEffectHandler(
                service = Service().apply {
                    messageQuery = this@CounterDI.messageQuery
                }
            )
        )
    }
}
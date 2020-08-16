package com.company.projectName.android.clean.di

import com.company.projectName.android.base.mvu.Component
import com.company.projectName.android.clean.domain.DomainProcessor
import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.core.Program
import com.company.projectName.android.clean.domain.feature.counter.CounterEffectHandler
import com.company.projectName.android.clean.domain.feature.counter.CounterReducer
import com.company.projectName.android.clean.domain.feature.counter.CounterState

@ExperimentalStdlibApi
class CounterDI(
    private val messageQuery: MessageQuery,
    private val component: Component
) {

    private fun createService() = DomainProcessor().apply {
        messageQuery = this@CounterDI.messageQuery
    }

    fun createProgram(): Program {
        return Program(
            reducer = CounterReducer(),
            messageQuery = messageQuery,
            effectHandler = CounterEffectHandler(
                service = createService()
            ),
            initialState = CounterState(
                isProgress = false,
                counter = 0
            ),
            component = component
        )
    }
}
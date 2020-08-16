package com.company.projectName.android.clean.di

import com.company.projectName.android.base.mvu.Component
import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.core.Program
import com.company.projectName.android.clean.domain.feature.counter.*

@ExperimentalStdlibApi
class CounterDI(
    private val messageQuery: MessageQuery,
    private val component: Component
) {

    private fun createProcessor() = DomainProcessor(messageQuery)

    fun createProgram(): Program {
        return Program(
            reducer = counterReducer,
            messageQuery = messageQuery,
            effectHandler = CounterEffectHandler(
                processor = createProcessor()
            ),
            initialState = CounterState(
                isProgress = false,
                counter = 0
            ),
            component = component
        )
    }
}
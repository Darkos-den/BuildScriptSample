package com.company.projectName.android.clean.di

import com.company.projectName.android.base.mvu.Component
import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.core.Program
import com.company.projectName.android.clean.domain.feature.counter.CounterEffectHandler
import com.company.projectName.android.clean.domain.feature.counter.CounterState
import com.company.projectName.android.clean.domain.feature.counter.DomainProcessor
import com.company.projectName.android.clean.domain.feature.namedCounter.NamedCounterState
import com.company.projectName.android.clean.domain.feature.namedCounter.namedCounterReducer

@ExperimentalStdlibApi
class NamedCounterDi(
    private val messageQuery: MessageQuery,
    private val component: Component
) {
    private fun createProcessor() = DomainProcessor(messageQuery)

    fun createProgram(): Program {
        return Program(
            reducer = namedCounterReducer,
            messageQuery = messageQuery,
            effectHandler = CounterEffectHandler(
                processor = createProcessor()
            ),
            initialState = NamedCounterState(
                name = "name",
                counter = CounterState(
                    isProgress = false,
                    counter = 0
                )
            ),
            component = component
        )
    }
}
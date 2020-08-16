package com.company.projectName.android.clean.domain.core

import com.company.projectName.android.base.mvu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalStdlibApi
class Program(
    private val reducer: Reducer,
    private val effectHandler: EffectHandler,
    private val messageQuery: MessageQuery,
    private val component: Component,
    initialState: ScreenState
) {

    private val job = CoroutineScope(Dispatchers.IO).launch {
        while (true) {
            messageQuery.channel.receive().let {
                reducer.update(it.state, it.msg)
            }.also {
                withContext(Dispatchers.Main) {
                    component.render(it.state)
                }
            }.also {
                messageQuery.state = it.state

                //remove current message from queue
                messageQuery.msgQueue.let {
                    if (it.size > 0) {
                        it.removeFirst()
                    }
                }
                //and send a new msg to relay if any
                messageQuery.loop()
            }.takeIf {
                it.cmd !is None
            }?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    effectHandler.call(it.cmd).let {
                        when (it) {
                            is Idle -> Unit
                            else -> messageQuery.msgQueue.addLast(it)
                        }

                        messageQuery.loop()
                    }
                }
            }
        }
    }

    init {
        component.render(initialState)
        messageQuery.state = initialState
    }

    fun start() {
        job.start()
    }

    fun clear() {
        job.cancel()
    }
}
package com.company.projectName.android.home

import com.company.projectName.android.base.mvu.*
import com.company.projectName.android.counter.MessageQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalStdlibApi
class Program(
    private val reducer: Reducer,
    private val effectHandler: EffectHandler,
    private val messageQuery: MessageQuery
) {
    private lateinit var component: Component

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

    fun init(
        initialState: ScreenState,
        component: Component
    ) {
        messageQuery.state = initialState
        this.component = component

        CoroutineScope(Dispatchers.IO).launch {
            job.join()
        }
    }

    fun clear() {
        job.cancel()
    }
}
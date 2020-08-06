package com.company.projectName.android.base

import com.company.projectName.android.base.mvu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalStdlibApi
class Program {

    lateinit var state: ScreenState
        private set
    private lateinit var component: Component

    private val msgQueue = ArrayDeque<Msg>()

    private val channel = Channel<ScreenMessageData>()

    private val job = CoroutineScope(Dispatchers.IO).launch {
        while (true) {
            channel.receive().let {
                component.update(it.state, it.msg)
            }.also {
                withContext(Dispatchers.Main) {
                    component.render(it.state)
                }
            }.also {
                this@Program.state = it.state

                //remove current message from queue
                if (msgQueue.size > 0) {
                    msgQueue.removeFirst()
                }
                //and send a new msg to relay if any
                loop()
            }.takeIf {
                it.cmd !is None
            }?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    component.call(it.cmd).let {
                        when (it) {
                            is Idle -> Unit
                            else -> msgQueue.addLast(it)
                        }

                        loop()
                    }
                }
            }
        }
    }

    fun init(
        initialState: ScreenState,
        component: Component
    ) {
        state = initialState
        this.component = component

        CoroutineScope(Dispatchers.IO).launch {
            job.join()
        }
    }

    private fun loop() {
        if (msgQueue.size > 0) {
            processMessage()
        }
    }

    fun accept(msg: Msg) {
        msgQueue.addLast(msg)
        if (msgQueue.size == 1) {
            processMessage()
        }
    }

    private fun processMessage() {
        ScreenMessageData(
            state = state,
            msg = msgQueue.first()
        ).let {
            CoroutineScope(Dispatchers.IO).launch {
                channel.send(it)
            }
        }
    }

    fun clear() {
        job.cancel()
    }
}
package com.company.projectName.android.counter

import com.company.projectName.android.base.mvu.Msg
import com.company.projectName.android.base.mvu.ScreenMessageData
import com.company.projectName.android.base.mvu.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

@ExperimentalStdlibApi
class MessageQuery {

    val channel = Channel<ScreenMessageData>()
    val msgQueue = ArrayDeque<Msg>()
    lateinit var state: ScreenState

    fun loop() {
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
}
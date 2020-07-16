package com.company.core.utils

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import platform.darwin.DISPATCH_TIME_NOW
import platform.darwin.NSEC_PER_MSEC
import platform.darwin.dispatch_after
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_time
import kotlin.coroutines.CoroutineContext

actual fun dispatch(withScope: CoroutineScope, mainDispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Unit) {
    withScope.launch(mainDispatcher, block = block)
}

internal actual class CoroutinesDispatcher {
    actual val mainDispatcher: CoroutineDispatcher =
        MainLoopDispatcher()
    actual val ioDispatcher: CoroutineDispatcher =
        MainLoopDispatcher()
}

@OptIn(InternalCoroutinesApi::class, ExperimentalCoroutinesApi::class)
private class MainLoopDispatcher : CoroutineDispatcher(), Delay {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) {
            block.run()
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (timeMillis * NSEC_PER_MSEC.toLong())), dispatch_get_main_queue()) {
            with(continuation) {
                resumeUndispatched(Unit)
            }
        }
    }
}

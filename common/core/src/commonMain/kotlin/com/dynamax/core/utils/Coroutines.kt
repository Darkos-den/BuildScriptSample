package com.dynamax.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

expect fun dispatch(withScope: CoroutineScope = GlobalScope, mainDispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Unit)

internal expect class CoroutinesDispatcher() {
    val mainDispatcher: CoroutineDispatcher
    val ioDispatcher: CoroutineDispatcher
}

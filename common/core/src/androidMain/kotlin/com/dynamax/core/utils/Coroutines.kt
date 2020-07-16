package com.dynamax.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

actual fun dispatch(withScope: CoroutineScope, mainDispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Unit) {
    withScope.launch(mainDispatcher, block = block)
}

internal actual class CoroutinesDispatcher {
    actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
}

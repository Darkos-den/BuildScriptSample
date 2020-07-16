package com.dynamax.core

import com.dynamax.core.utils.CoroutinesDispatcher
import com.dynamax.projectName.repository.TodoRepository

class Core {
    suspend fun getTodo(): String {
        return TodoRepository().getTodo(CoroutinesDispatcher().ioDispatcher)
    }
}

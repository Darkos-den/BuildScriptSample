package com.company.core

import com.company.core.utils.CoroutinesDispatcher
import com.company.projectName.repository.TodoRepository

class Core {
    suspend fun getTodo(): String {
        return TodoRepository().getTodo(CoroutinesDispatcher().ioDispatcher)
    }
}

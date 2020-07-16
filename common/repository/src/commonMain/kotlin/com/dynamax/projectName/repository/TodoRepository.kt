package com.dynamax.projectName.repository

import com.dynamax.network.service.TodoService
import kotlin.coroutines.CoroutineContext


class TodoRepository() {
    suspend fun getTodo(context: CoroutineContext): String {
        return TodoService().getTodo(context)
    }
}

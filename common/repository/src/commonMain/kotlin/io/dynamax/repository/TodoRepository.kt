package io.dynamax.repository

import io.dynamax.network.service.TodoService
import kotlin.coroutines.CoroutineContext


class TodoRepository() {
    suspend fun getTodo(context: CoroutineContext): String {
        return TodoService().getTodo(context)
    }
}

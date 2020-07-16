package com.company.projectName.repository

import com.company.network.service.TodoService
import kotlin.coroutines.CoroutineContext


class TodoRepository() {
    suspend fun getTodo(context: CoroutineContext): String {
        return TodoService().getTodo(context)
    }
}

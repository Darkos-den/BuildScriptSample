package com.company.network.service

import com.company.network.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class TodoService() {

    suspend fun getTodo(context: CoroutineContext) = withContext(context) {
        HttpClient().client.get<String> { url("${HttpClient.baseURL}/todos") }
    }
}

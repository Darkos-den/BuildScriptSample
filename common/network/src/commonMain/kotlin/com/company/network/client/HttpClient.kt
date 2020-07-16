package com.company.network.client

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging

class HttpClient {

    companion object {
        const val baseURL = "https://jsonplaceholder.typicode.com"
    }

    val client = HttpClient {
        install(JsonFeature) { serializer = KotlinxSerializer() }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
    }
}

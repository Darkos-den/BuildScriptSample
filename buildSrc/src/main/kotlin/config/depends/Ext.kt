package config.depends

import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun DependencyHandlerScope.applyDependencies(deps: List<Depend>) {
    deps.forEach {
        it.method(it.depend)
    }
}

fun KotlinDependencyHandler.applyDependencies(deps: List<Depend>) {
    deps.forEach {
        when (it.method) {
            "implementation" -> implementation(it.depend)
            "api" -> api(it.depend)
            "compileOnly" -> compileOnly(it.depend)
            else -> throw IllegalArgumentException("${it.method} not supported")
        }
    }
}
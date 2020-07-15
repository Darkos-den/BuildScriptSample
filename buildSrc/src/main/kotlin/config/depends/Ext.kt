package config.depends

import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun IDepend.processToString(): String {
    return "$depend:$version"
}

fun List<Depend>.apply(scope: DependencyHandlerScope) {
    this.forEach {
        with(scope) {
            it.method(it.depend)
        }
    }
}

fun List<Depend>.apply(scope: KotlinDependencyHandler) {
    this.forEach {
        with(scope) {
            when (it.method) {
                "implementation" -> implementation(it.depend)
                "api" -> api(it.depend)
                "compileOnly" -> compileOnly(it.depend)
                else -> throw IllegalArgumentException("${it.method} not supported")
            }
        }
    }
}
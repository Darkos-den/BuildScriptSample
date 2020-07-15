package config.depends

import org.gradle.api.artifacts.dsl.DependencyHandler

fun depend(block: () -> Pair<String, IDepend>): Depend {
    return block.invoke().let {
        Depend(it.first, it.second)
    }
}

fun implementation(depend: IDepend): Depend {
    return Depend("implementation", depend)
}

fun api(depend: IDepend): Depend {
    return Depend("api", depend)
}

fun compileOnly(depend: IDepend): Depend {
    return Depend("compileOnly", depend)
}

fun DependencyHandler.classPath(depend: IDepend) = add("classpath", depend.full)
package config.depends

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
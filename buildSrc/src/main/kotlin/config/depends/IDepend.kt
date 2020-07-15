package config.depends

interface IDepend {
    val depend: String
    val version: String

    val classpath: String
        get() = "$depend:$version"
}
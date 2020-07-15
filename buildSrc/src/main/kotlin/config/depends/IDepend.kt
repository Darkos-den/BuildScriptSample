package config.depends

interface IDepend {
    val depend: String
    val version: String

    val full: String
        get() = "$depend:$version"
}
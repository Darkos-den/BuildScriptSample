package config

import config.depends.IDepend
import config.depends.implementation

object Libraries {

    enum class Kotlin(
        override val depend: String
    ) : IDepend {
        JDK("org.jetbrains.kotlin:kotlin-stdlib-jdk8"),
        STDLIB("org.jetbrains.kotlin:kotlin-stdlib"),
        REFLECT("org.jetbrains.kotlin:kotlin-reflect"),
        GRADLE("org.jetbrains.kotlin:kotlin-gradle-plugin"),
        COMMON("org.jetbrains.kotlin:kotlin-stdlib-common");

        override val version =
            Versions.kotlin
    }

    enum class Ktor(
        override val depend: String
    ) : IDepend {
        CORE("io.ktor:ktor-client-core"),
        SERIALIZATION("io.ktor:ktor-client-serialization"),
        LOGGING("io.ktor:ktor-client-logging"),
        ANDROID("io.ktor:ktor-client-android");

        override val version = Versions.ktor

        companion object {
            val defaultCommon = listOf(
                implementation(CORE),
                implementation(SERIALIZATION),
                implementation(LOGGING)
            ).toTypedArray()

            val defaultAndroid = listOf(
                implementation(CORE),
                implementation(ANDROID)
            ).toTypedArray()
        }
    }

    enum class Coroutines(
        override val depend: String
    ) : IDepend {
        COMMON("org.jetbrains.kotlinx:kotlinx-coroutines-core-common"),
        ANDROID("org.jetbrains.kotlinx:kotlinx-coroutines-android"),
        NATIVE("org.jetbrains.kotlinx:kotlinx-coroutines-core-native");

        override val version: String
            get() = Versions.coroutines

    }

    enum class Serialization(
        override val depend: String
    ) : IDepend {
        COMMON("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common"),
        RUNTIME("org.jetbrains.kotlinx:kotlinx-serialization-runtime"),
        NATIVE("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native");

        override val version: String
            get() = Versions.serialization

    }

    enum class AndroidGradle(
        override val depend: String
    ) : IDepend {
        GRADLE("com.android.tools.build:gradle");

        override val version: String
            get() = Versions.gradle

    }

    enum class Detekt(
        override val depend: String
    ) : IDepend {
        GRADLE("io.gitlab.arturbosch.detekt:detekt-gradle-plugin");

        override val version: String
            get() = Versions.detekt

    }
}
import config.depends.IDepend
import config.depends.implementation

object Libs {

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
        SERIALIZATION_JVM("io.ktor:ktor-client-serialization-jvm"),
        SERIALIZATION_NATIVE("io.ktor:ktor-client-serialization-native"),
        LOGGING("io.ktor:ktor-client-logging"),
        LOGGING_JVM("io.ktor:ktor-client-logging-jvm"),
        LOGGING_IOS("io.ktor:ktor-client-logging-native"),
        OK_HTTP("io.ktor:ktor-client-okhttp"),
        IOS("io.ktor:ktor-client-ios"),
        ANDROID("io.ktor:ktor-client-android");

        override val version = Versions.ktor

        companion object {
            val defaultCommon = listOf(
                implementation(CORE),
                implementation(SERIALIZATION),
                implementation(LOGGING)
            ).toTypedArray()

            val defaultAndroid = listOf(
                implementation(OK_HTTP),
                implementation(SERIALIZATION_JVM),
                implementation(LOGGING_JVM)
            ).toTypedArray()

            val defaultIos = listOf(
                implementation(IOS),
                implementation(SERIALIZATION_NATIVE),
                implementation(LOGGING_IOS)
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

    object AndroidX{
        enum class Activity(
            override val depend: String
        ) : IDepend {
            KTX("androidx.activity:activity-ktx");

            override val version: String
                get() = Versions.AndroidX.activity

        }
        enum class AppCompat(
            override val depend: String
        ) : IDepend {
            CORE("androidx.appcompat:appcompat");

            override val version: String
                get() = Versions.AndroidX.appCompat

        }
        enum class Compose(
            override val depend: String
        ) : IDepend {
            CORE("androidx.ui:ui-core"),
            RUNTIME("androidx.compose:compose-runtime"),
            FOUNDATION("androidx.ui:ui-foundation"),
            LAYOUT("androidx.ui:ui-layout"),
            ANIMATION("androidx.ui:ui-animation"),
            MATERIAL("androidx.ui:ui-material"),
            MATERIAL_EXTENDED("androidx.ui:ui-material-icons-extended"),
            TOOLING("androidx.ui:ui-tooling"),
            LIVE_DATA("androidx.ui:ui-livedata");

            override val version: String
                get() = Versions.AndroidX.compose

            companion object{
                val all = values().map {
                    implementation(it)
                }.toTypedArray()
            }

        }
    }
}
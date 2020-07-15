object Kotlin {

    const val version = "1.3.72"

    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"

    const val testCommon = "org.jetbrains.kotlin:kotlin-test-common"
    const val testAnnotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common"
    const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$version"
}

object AndroidX {

    object Versions {
        const val appCompat = "1.1.0"
        const val activityVersion = "1.2.0-alpha05"
        const val compose = "0.1.0-dev13"
        const val composeCompiler = "1.3.70-dev-withExperimentalGoogleExtensions-20200424"
    }

    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    const val composeCore = "androidx.ui:ui-core:${Versions.compose}"
    const val composeRuntime = "androidx.compose:compose-runtime:${Versions.compose}"
    const val composeFoundation = "androidx.ui:ui-foundation:${Versions.compose}"
    const val composeLayout = "androidx.ui:ui-layout:${Versions.compose}"
    const val composeAnimation = "androidx.ui:ui-animation:${Versions.compose}"
    const val composeMaterial = "androidx.ui:ui-material:${Versions.compose}"
    const val composeMaterialExtended = "androidx.ui:ui-material-icons-extended:${Versions.compose}"
    const val composeTooling = "androidx.ui:ui-tooling:${Versions.compose}"
    const val composeLiveData = "androidx.ui:ui-livedata:${Versions.compose}"
}

object Coroutines {

    private const val version = "1.3.7"

    const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$version"

    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$version"
}

object Serialization {

    private const val version = "0.20.0"

    const val common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$version"
    const val runtime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$version"
    const val native = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$version"
}

object Ktor {

    private const val version = "1.3.2"

    const val clientOkttp = "io.ktor:ktor-client-okhttp:$version"
    const val clientSerializationJvm = "io.ktor:ktor-client-serialization-jvm:$version"
    const val clientLoggingJvm = "io.ktor:ktor-client-logging-jvm:$version"

    const val clientIos = "io.ktor:ktor-client-ios:$version"
    const val clientSerializationIos = "io.ktor:ktor-client-serialization-native:$version"
    const val clientLoggingIos = "io.ktor:ktor-client-logging-native:$version"
}


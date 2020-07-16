import config.depends.applyDependencies
import config.depends.implementation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

val commonDeps = listOf(
    implementation(Libs.Kotlin.STDLIB),
    *Libs.Ktor.defaultCommon,
    implementation(Libs.Coroutines.COMMON),
    implementation(Libs.Serialization.COMMON)
)

val androidDeps = listOf(
    *Libs.Ktor.defaultAndroid
)

val iosDeps = listOf(
    implementation(Libs.Coroutines.NATIVE),
    implementation(Libs.Serialization.NATIVE),
    *Libs.Ktor.defaultIos
)

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version (Versions.kotlin)

    id("com.android.library")
    id("app-config-android")
}

applyMultiPlatformSourceSets()

kotlin {
    val uniqueName = "${project.rootProject.name}${project.name.capitalize()}"

    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework(uniqueName)
        }
    }

    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                applyDependencies(commonDeps)
            }
        }

        val androidMain by getting {
            dependencies {
                applyDependencies(androidDeps)
            }
        }

        val iosMain by getting {
            dependencies {
                applyDependencies(iosDeps)
            }
        }
    }
}

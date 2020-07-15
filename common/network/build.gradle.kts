import config.depends.apply
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

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version (Kotlin.version)

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
                commonDeps.apply(this)
            }
        }

        val androidMain by getting {
            dependencies {
                androidDeps.apply(this)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(Ktor.clientIos)
                implementation(Ktor.clientSerializationIos)
                implementation(Ktor.clientLoggingIos)

                implementation(Coroutines.native)
                implementation(Serialization.native)
            }
        }
    }
}

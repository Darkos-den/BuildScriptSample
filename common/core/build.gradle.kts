import config.depends.apply
import config.depends.implementation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")

    id("com.android.library")
    id("app-config-android")
}

applyMultiPlatformSourceSets()

val commonDeps = listOf(
    implementation(Libs.Kotlin.STDLIB),
    implementation(Libs.Coroutines.COMMON)
)

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
                implementation(project(":common:repository"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Libs.Coroutines.ANDROID.full)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(Libs.Coroutines.NATIVE.full)
            }
        }
    }
}

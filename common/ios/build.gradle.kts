import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    val uniqueName = "${project.rootProject.name}${project.name.capitalize()}"

    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
            if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
                ::iosArm64
            else
                ::iosX64

    iOSTarget("ios") {
        binaries {
            framework(uniqueName) {
                export(project(":common:core"))
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":common:core"))

                implementation(Kotlin.stdlib)
            }
        }
    }
}

val buildXCFramework = tasks.register<XCFrameworkTask>("buildXCFramework") { singleTarget = true }
tasks.build { finalizedBy(buildXCFramework) }

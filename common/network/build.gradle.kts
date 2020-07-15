import config.Libraries
import config.depends.apply
import config.depends.implementation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

val deps = listOf(
    implementation(Libraries.Kotlin.STDLIB),
    *Libraries.Ktor.defaultCommon,
    implementation(Libraries.Coroutines.COMMON),
    implementation(Libraries.Serialization.COMMON)
)

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version (Kotlin.version)

    id("com.android.library")
}

android {
    defaultConfig {
        compileSdkVersion(Android.Versions.compileSdk)
        targetSdkVersion(Android.Versions.targetSdk)
        minSdkVersion(Android.Versions.minSdk)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("main").apply {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDirs("src/androidMain/kotlin")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
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
            framework(uniqueName)
        }
    }

    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                deps.apply(this)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Ktor.clientOkttp)
                implementation(Ktor.clientSerializationJvm)
                implementation(Ktor.clientLoggingJvm)

                implementation(Coroutines.android)
                implementation(Serialization.runtime)
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

        val commonTest by getting {
            dependencies {
                implementation(Kotlin.testCommon)
                implementation(Kotlin.testAnnotationsCommon)
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(Kotlin.testJunit)
            }
        }
    }
}

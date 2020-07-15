import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")

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
                implementation(project(":common:repository"))

                implementation(Kotlin.stdlib)
                implementation(Coroutines.common)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Coroutines.android)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(Coroutines.native)
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

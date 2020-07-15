import config.depends.apply
import config.depends.implementation

plugins {
    id("com.android.application")
    kotlin("android")
    id("app-config-android")
}

val deps = listOf(
    implementation(Libs.Kotlin.STDLIB),
    implementation(Libs.AndroidX.Activity.KTX),
    implementation(Libs.AndroidX.AppCompat.CORE),
    *Libs.AndroidX.Compose.all,
    implementation(Libs.Coroutines.ANDROID)
)

androidApplicationConfig()

android {
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

    packagingOptions {
        exclude("**/*.kotlin_module")
        exclude("**/*.version")
        exclude("**/kotlin/**")
        exclude("**/*.txt")
        exclude("**/*.xml")
        exclude("**/*.properties")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = config.Versions.AndroidX.composeCompiler
        kotlinCompilerExtensionVersion = config.Versions.AndroidX.compose
    }
}

dependencies {
    deps.apply(this)

    implementation(project(":common:core"))
}

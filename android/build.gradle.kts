plugins {
    id("com.android.application")
    kotlin("android")
    id("app-config-android")
}

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
        kotlinCompilerVersion = AndroidX.Versions.composeCompiler
        kotlinCompilerExtensionVersion = AndroidX.Versions.compose
    }
}

dependencies {
    implementation(project(":common:core"))

    implementation(Kotlin.stdlib)

    // AndroidX
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.appCompat)

    // Compose
    implementation(AndroidX.composeRuntime)
    implementation(AndroidX.composeCore)
    implementation(AndroidX.composeLayout)
    implementation(AndroidX.composeMaterial)
    implementation(AndroidX.composeTooling)
    implementation(AndroidX.composeLiveData)
    implementation(AndroidX.composeFoundation)
    implementation(AndroidX.composeAnimation)
    implementation(AndroidX.composeMaterialExtended)

    // Coroutines
    implementation(Coroutines.android)
}

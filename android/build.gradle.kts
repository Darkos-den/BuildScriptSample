plugins {
    id("com.android.application")
    kotlin("android")
}

val versionMajor = 0
val versionMinor = 0
val versionPatch = 1

android {
    defaultConfig {
        applicationId = "io.dynamax.android"
        versionCode = versionMajor * 100 + versionMinor * 10 + versionPatch
        versionName = "$versionMajor.$versionMinor.$versionPatch"
        vectorDrawables.useSupportLibrary = true
        compileSdkVersion(Android.Versions.compileSdk)
        targetSdkVersion(Android.Versions.targetSdk)
        minSdkVersion(Android.Versions.minSdk)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

buildscript {
    repositories {
        jcenter()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Kotlin.gradle)
        classpath(Android.gradle)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }

    pluginManager.withPlugin("kotlin-multiplatform") {
        val kotlinExtension = project.extensions.getByName("kotlin") as KotlinMultiplatformExtension
        val uniqueName = "${project.rootProject.name}.${project.name}"

        // Workaround for https://youtrack.jetbrains.com/issue/KT-36721 (modules names conflict with system modules)
        // Let's remove after Kotlin 1.4 release
        kotlinExtension.targets.withType(KotlinNativeTarget::class.java) {
            compilations["main"].kotlinOptions.freeCompilerArgs += listOf("-module-name", uniqueName, "-Xopt-in=kotlin.RequiresOptIn")
        }
    }
}

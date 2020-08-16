import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

buildscript {
    val kotlin_version by extra("1.3.61")
    repositories {
        jcenter()
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(Libs.Kotlin.GRADLE.full)
        classpath(Libs.AndroidGradle.GRADLE.full)
        classpath(Libs.Detekt.GRADLE.full)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
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

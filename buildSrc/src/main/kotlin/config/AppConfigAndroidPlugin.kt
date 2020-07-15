package config

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AppConfigAndroidPlugin : Plugin<Project> {

    private val Project.android: BaseExtension
        get() = extensions.findByName("android") as? BaseExtension
            ?: error("Not an Android module: $name")

    override fun apply(project: Project) =
        with(project) {
            androidConfig()
            dependenciesConfig()
        }

    private fun Project.androidConfig() {
        android.run {
            compileSdkVersion(compileVersion)
            defaultConfig {
                minSdkVersion(minVersion)
                targetSdkVersion(targetVersion)
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = true
                    proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
                }
                getByName("debug") {
                    isMinifyEnabled = false
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }

    private fun Project.dependenciesConfig() {
//        dependencies {
//            "detektPlugins"(project(":detect"))
//        }
    }

    companion object {
        private const val compileVersion = 29
        private const val minVersion = 23
        private const val targetVersion = 29
    }
}
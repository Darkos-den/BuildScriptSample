import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Project '$name' is not an Android module")

fun Project.androidApplicationConfig(
    appId: String = Versions.Project.applicationId,
    appVersionCode: Int = Versions.Project.versionCodeValue,
    appVersionName: String = Versions.Project.versionNameValue
) {
    android.run {
        defaultConfig {
            applicationId = appId
            versionCode = appVersionCode
            versionName = appVersionName
            multiDexEnabled = true
        }
    }
}

fun Project.applyMultiPlatformSourceSets(){
    android.run {
        sourceSets {
            getByName("main").apply {
                setRoot("src/androidMain")
            }
        }
    }
}
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

open class XCFrameworkTask : DefaultTask() {

    @Input
    var buildType = "Debug"

    @Input
    var frameworkName = "${project.rootProject.name}${project.name.capitalize()}"

    @Input
    var outputDir = "${project.rootDir}/build/iOSXCFrameworks"

    @Input
    var singleTarget = false

    @Input
    var targetName = "ios"

    private val arm64Framework
        get() = "${project.buildDir}/bin/iosArm64/${frameworkName}${buildType}Framework/$frameworkName.framework"

    private val x64Framework
        get() = "${project.buildDir}/bin/iosX64/${frameworkName}${buildType}Framework/$frameworkName.framework"

    private val iosFramework
        get() = "${project.buildDir}/bin/$targetName/${frameworkName}${buildType}Framework/$frameworkName.framework"

    private val outputFile
        get() = "$frameworkName.xcframework"

    private val output
        get() = "$outputDir/$outputFile"

    @TaskAction
    fun processTask() {
        project.exec {
            deleteExistingFramework()
            commandLine = getTaskCommand()
        }
    }

    private fun getTaskCommand(): List<String> {
        return listOf("xcodebuild", "-create-xcframework") +
                getFrameworks() +
                listOf("-output", output)
    }

    private fun getFrameworks(): List<String> {
        return if (singleTarget) {
            listOf("-framework", iosFramework)
        } else {
            listOf(
                "-framework", arm64Framework,
                "-framework", x64Framework
            )
        }
    }

    private fun deleteExistingFramework() {
        if (project.delete(output))
            println("Old framework deleted: $outputFile")
    }
}

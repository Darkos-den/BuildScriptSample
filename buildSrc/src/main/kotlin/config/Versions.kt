package config

object Versions {

    object Project {
        const val applicationId = "com.company.projectName"

        private const val patch = 1
        private const val minor = 0
        private const val major = 0

        const val versionCodeValue = 1
        const val versionNameValue = "${major}.${minor}.${patch}"
    }

    const val kotlin = "1.3.72"
    const val ktor = "1.3.2"
    const val coroutines = "1.3.7"
    const val serialization = "0.20.0"
    const val detekt = "1.10.0-RC1"
    const val gradle = "4.2.0-alpha04"

    object AndroidX{
        const val activity = "1.2.0-alpha05"
        const val appCompat = "1.1.0"
        const val compose = "0.1.0-dev13"
        const val composeCompiler = "1.3.70-dev-withExperimentalGoogleExtensions-20200424"
    }
}
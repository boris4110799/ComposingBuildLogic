import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.create
import tw.boris4110799.composing.convention.dsl.ComposingExtension

class ComposingSettingsConventionPlugin : Plugin<Settings> {
    companion object {
        internal const val COMPOSING_EXTENSION = "composing"
    }

    override fun apply(target: Settings) {
        val composing = target.extensions.create<ComposingExtension>(COMPOSING_EXTENSION)

        composing.defaultLibraryName.convention("libs")
        composing.multiplatform.includeJvm.convention(true)
        composing.multiplatform.includeIos.convention(false)
        composing.maven.repository.url.convention("")

        target.gradle.beforeProject(
            Action {
                extensions.add(
                    ComposingExtension::class.java, COMPOSING_EXTENSION, composing
                )
            })
    }
}

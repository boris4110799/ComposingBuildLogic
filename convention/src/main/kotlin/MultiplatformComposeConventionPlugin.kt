import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.resources.ResourcesExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import tw.boris4110799.composing.convention.configureComposeMultiplatform

class MultiplatformComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.compose")
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureComposeMultiplatform(this)
        }

        extensions.configure<ComposeExtension> {
            extensions.configure<ResourcesExtension> {
                generateResClass = ResourcesExtension.ResourceClassGeneration.Never
            }
        }
    }
}

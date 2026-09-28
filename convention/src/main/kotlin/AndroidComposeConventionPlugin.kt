import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import tw.boris4110799.composing.convention.versionOf

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        dependencies {
            val composeBom = platform("androidx.compose:compose-bom:${versionOf("compose-bom")}")

            add("implementation", composeBom)
            add("implementation", "androidx.compose.runtime:runtime")
        }
    }
}

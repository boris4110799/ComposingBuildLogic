package tw.boris4110799.composing.convention

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform(extension: KotlinMultiplatformExtension) =
    extension.apply {
        sourceSets.apply {
            getByName("commonMain").dependencies {
                implementation("org.jetbrains.compose.runtime:runtime") {
                    version {
                        require(versionOf("cmp-runtime"))
                    }
                }
            }
        }
    }

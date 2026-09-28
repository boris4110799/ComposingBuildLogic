package tw.boris4110799.composing.convention

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import com.android.build.api.withAndroid
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(ExperimentalKotlinGradlePluginApi::class)
internal fun Project.configureKotlinMultiplatform(extension: KotlinMultiplatformExtension) =
    extension.apply {
        applyDefaultHierarchyTemplate {
            common {
                group("jvmAndAndroid") {
                    withJvm()
                    withAndroid()
                }
            }
        }

        configure<KotlinMultiplatformAndroidLibraryTarget> {
            configureAndroidMultiplatformLibrary(this)
        }

        if (composing.multiplatform.includeJvm.get()) {
            jvm()
        }

        if (composing.multiplatform.includeIos.get()) {
            iosArm64()
            iosSimulatorArm64()
        }

        // Opt-in
        compilerOptions {
            freeCompilerArgs.addAll("-Xexpect-actual-classes")
        }

        withSourcesJar()
    }

package tw.boris4110799.composing.convention

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal fun Project.configureAndroidMultiplatformLibrary(extension: KotlinMultiplatformAndroidLibraryTarget) =
    extension.apply {
        namespace = modulePackage
        compileSdk = versionOf("android-compileSdk").toInt()
        minSdk = versionOf("android-minSdk").toInt()

        withHostTest {
            targetSdk {
                release(versionOf("android-compileSdk").toInt())
            }
        }

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

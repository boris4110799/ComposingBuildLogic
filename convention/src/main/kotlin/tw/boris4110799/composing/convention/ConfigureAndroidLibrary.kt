package tw.boris4110799.composing.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureAndroidLibrary(extension: LibraryExtension) =
    extension.apply {
        namespace = modulePackage
        compileSdk = versionOf("android-compileSdk").toInt()

        defaultConfig {
            minSdk = versionOf("android-minSdk").toInt()
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        publishing {
            singleVariant("release") {
                withSourcesJar()
            }
        }
    }

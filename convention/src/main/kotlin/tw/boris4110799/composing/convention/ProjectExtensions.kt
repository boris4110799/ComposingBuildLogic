package tw.boris4110799.composing.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import tw.boris4110799.composing.convention.dsl.ComposingExtension

/**
 * The settings of Composing Convention plugin.
 */
internal val Project.composing: ComposingExtension
    get() = extensions.findByType<ComposingExtension>() ?: error(
        """Convention plugins require the settings plugin. In settings.gradle.kts:
        |plugins {
        |   id("com.ptc.gradle.convention.settings")
        |}
        |
        |composing {
        |   basePackage = "com.example.project"
        |}""".trimMargin()
    )

/**
 * The version catalog (`gradle/libs.versions.toml`) of Project that import this Convention plugin.
 */
internal val Project.libs: VersionCatalog
    get() = runCatching {
        extensions.getByType<VersionCatalogsExtension>()
            .named(composing.defaultLibraryName.orFail("composing.defaultLibraryName"))
    }.getOrElse {
        val name = composing.defaultLibraryName.orFail("composing.defaultLibraryName")

        error(
            "Version catalog '$name' not found. Create gradle/libs.versions.toml with [versions] version, android-compileSdk, android-minSdk."
        )
    }

/** The namespace derive from module path. */
internal val Project.modulePackage: String
    get() = removeOverlap(
        composing.basePackage.orFail("composing.basePackage"),
        path.replace(":", ".").replace("-", ".")
    )

/**
 * Read the version of library from `gradle/libs.versions.toml`.
 */
internal fun Project.versionOf(alias: String): String = libs.findVersion(alias).orElseThrow {
    IllegalStateException("Missing [versions] '$alias' in gradle/libs.versions.toml.")
}.requiredVersion

/** Read the property of settings. */
internal fun <T : Any> Property<T>.orFail(alias: String): T =
    orNull ?: error("Missing '$alias'. Set it in settings.gradle.kts.")

internal fun removeOverlap(
    prefix: String,
    suffix: String
): String {
    for (index in suffix.indices) {
        if (prefix.endsWith(suffix.take(index + 1))) {
            return prefix + suffix.drop(index + 1)
        }
    }

    return prefix + suffix
}

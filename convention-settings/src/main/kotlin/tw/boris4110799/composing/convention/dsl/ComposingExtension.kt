package tw.boris4110799.composing.convention.dsl

import org.gradle.api.Action
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

/**
 * The top level DSL of Composing Convention plugin.
 *
 * ```kotlin
 * plugins {
 *     id("tw.boris4110799.composing.convention.settings")
 * }
 *
 * composing {
 *     basePackage = "com.example.project"
 * }
 * ```
 */
abstract class ComposingExtension {
    /** The prefix of project package and maven group. */
    abstract val basePackage: Property<String>

    /** The name of default library. */
    abstract val defaultLibraryName: Property<String>

    @get:Nested
    abstract val multiplatform: ComposingMultiplatform

    @get:Nested
    abstract val maven: ComposingMaven

    fun multiplatform(action: Action<in ComposingMultiplatform>) = action.execute(multiplatform)

    fun maven(action: Action<in ComposingMaven>) = action.execute(maven)
}

package tw.boris4110799.composing.convention.dsl

import org.gradle.api.provider.Property

abstract class ComposingMultiplatform {
    /** Whether to include JVM target. */
    abstract val includeJvm: Property<Boolean>

    /** Whether to include iOS target. */
    abstract val includeIos: Property<Boolean>
}

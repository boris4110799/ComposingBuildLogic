package tw.boris4110799.composing.convention.dsl

import org.gradle.api.Action
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested

/**
 * The maven DSL of Composing Convention plugin.
 *
 * ```kotlin
 * plugins {
 *     id("tw.boris4110799.composing.convention.settings")
 * }
 *
 * composing {
 *     basePackage = "com.example.project"
 *
 *     maven {
 *         description = "Example project"
 *     }
 * }
 * ```
 */
abstract class ComposingMaven {

    /** Maven description. */
    abstract val description: Property<String>

    @get:Nested
    abstract val pom: ComposingMavenPom

    @get:Nested
    abstract val repository: ComposingMavenRepository

    fun pom(action: Action<in ComposingMavenPom>) = action.execute(pom)

    fun repository(action: Action<in ComposingMavenRepository>) = action.execute(repository)
}

/**
 * The maven pom DSL of Composing Convention plugin.
 *
 * ```kotlin
 * plugins {
 *     id("tw.boris4110799.composing.convention.settings")
 * }
 *
 * composing {
 *     basePackage = "com.example.project"
 *
 *     maven {
 *         description = "Example project"
 *
 *         pom {
 *             url = "https://dev.azure.com/…"
 *             scmConnection = "scm:git:git://…"
 *             scmDeveloperConnection = "scm:git:ssh://…"
 *         }
 *     }
 * }
 * ```
 */
abstract class ComposingMavenPom {

    /** POM `url`. */
    abstract val url: Property<String>

    /** POM `scm.connection`. */
    abstract val scmConnection: Property<String>

    /** POM `scm.developerConnection`. */
    abstract val scmDeveloperConnection: Property<String>

    /** POM `developer.id`. */
    abstract val developerId: Property<String>

    /** POM `developer.name`. */
    abstract val developerName: Property<String>
}

/**
 * The maven repository DSL of Composing Convention plugin.
 *
 * ```kotlin
 * plugins {
 *     id("tw.boris4110799.composing.convention.settings")
 * }
 *
 * composing {
 *     basePackage = "com.example.project"
 *
 *     maven {
 *         description = "Example project"
 *
 *         repository {
 *             url = "https://…"
 *             scmConnection = "scm:git:git://…"
 *             scmDeveloperConnection = "scm:git:ssh://…"
 *         }
 *     }
 * }
 * ```
 */
abstract class ComposingMavenRepository {
    companion object {
        const val GITHUB_CREDENTIALS_FILE = "github.properties"
        const val AZURE_DEVOPS_CREDENTIALS_FILE = "azure-devops.properties"
    }

    /** Maven repository name. */
    abstract val name: Property<String>

    /** Maven repository URL. */
    abstract val url: Property<String>

    /** maven repository credentials file. Must be properties file and have `username` and `password` keys. */
    abstract val credentialsFile: Property<String>
}

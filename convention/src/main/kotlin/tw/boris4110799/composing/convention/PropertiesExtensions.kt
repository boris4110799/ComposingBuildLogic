package tw.boris4110799.composing.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.kotlin.dsl.credentials
import org.gradle.kotlin.dsl.maven
import org.jetbrains.kotlin.konan.properties.loadProperties
import tw.boris4110799.composing.convention.dsl.ComposingMavenRepository
import java.io.File

/**
 * Wrap up the call of maven repository. Get `username` and `password` from [fileName].
 * @param fileName Must be `.properties` file.
 * @see ComposingMavenRepository
 */
fun RepositoryHandler.mavenRepository(
    project: Project,
    repositoryName: String,
    url: String,
    fileName: String,
): MavenArtifactRepository {
    val properties = loadProperties(File(project.rootDir, fileName).path)

    return maven(url) {
        name = repositoryName
        credentials(PasswordCredentials::class) {
            username = properties.getProperty("username", "")
            password = properties.getProperty("password", "")
        }
    }
}

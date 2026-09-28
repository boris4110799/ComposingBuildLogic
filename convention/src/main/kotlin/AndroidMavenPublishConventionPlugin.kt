import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import tw.boris4110799.composing.convention.composing
import tw.boris4110799.composing.convention.mavenRepository
import tw.boris4110799.composing.convention.orFail
import tw.boris4110799.composing.convention.versionOf

class AndroidMavenPublishConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("maven-publish")
        }

        extensions.configure<PublishingExtension> {
            publications.create<MavenPublication>(project.name) {
                groupId = composing.basePackage.orFail("composing.basePackage")
                version = versionOf("version")

                pom {
                    name.set("${rootProject.name}-${project.name}")
                    description.set(composing.maven.description.orFail("composing.description"))
                    url.set(composing.maven.pom.url)

                    scm {
                        url.set(composing.maven.pom.url)
                        connection.set(composing.maven.pom.scmConnection)
                        developerConnection.set(composing.maven.pom.scmDeveloperConnection)
                    }

                    developers {
                        developer {
                            id.set(composing.maven.pom.developerId)
                            name.set(composing.maven.pom.developerName)
                        }
                    }
                }
                afterEvaluate {
                    from(components.getByName("release"))
                }
            }
            repositories {
                if (composing.maven.repository.url.isPresent) {
                    mavenRepository(
                        target,
                        composing.maven.repository.name.orFail("composing.maven.repository.name"),
                        composing.maven.repository.url.orFail("composing.maven.repository.url"),
                        composing.maven.repository.credentialsFile.orFail("composing.maven.repository.credentialsFile")
                    )
                }
                mavenLocal()
            }
        }
    }
}

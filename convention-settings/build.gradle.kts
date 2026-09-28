import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "tw.boris4110799.composing"
version = libs.versions.version.get()

java {
    withSourcesJar()
}

kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins {
        register("settings") {
            id = "tw.boris4110799.composing.convention.settings"
            implementationClass = "ComposingSettingsConventionPlugin"
        }
    }
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("${rootProject.name}-${project.name}")
            description.set("The Convention and setup DSL of Composing Project.")
            url.set(providers.gradleProperty("REPOSITORY_URL"))

            developers {
                developer {
                    id.set("boris4110799")
                    name.set("BorisHuang")
                }
            }
        }
    }
    repositories {
        maven {
            name = "Github"
            url = uri(providers.gradleProperty("GITHUB_MAVEN_URL").get())

            val properties = loadProperties(File(rootDir, "github.properties").path)

            credentials(PasswordCredentials::class) {
                username = properties.getProperty("username", "")
                password = properties.getProperty("password", "")
            }
        }
        mavenLocal()
    }
}

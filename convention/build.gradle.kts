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

dependencies {
    api(project(":convention-settings"))

    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.kotlin.multiplatform.gradle.plugin)
    compileOnly(libs.compose.multiplatform.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidCompose") {
            id = "tw.boris4110799.composing.convention.androidCompose"
            implementationClass = "AndroidComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "tw.boris4110799.composing.convention.androidLibrary"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidMavenPublish") {
            id = "tw.boris4110799.composing.convention.androidMavenPublish"
            implementationClass = "AndroidMavenPublishConventionPlugin"
        }

        register("multiplatformCompose") {
            id = "tw.boris4110799.composing.convention.multiplatformCompose"
            implementationClass = "MultiplatformComposeConventionPlugin"
        }

        register("multiplatformLibrary") {
            id = "tw.boris4110799.composing.convention.multiplatformLibrary"
            implementationClass = "MultiplatformLibraryConventionPlugin"
        }

        register("multiplatformMavenPublish") {
            id = "tw.boris4110799.composing.convention.multiplatformMavenPublish"
            implementationClass = "MultiplatformMavenPublishConventionPlugin"
        }
    }
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("${rootProject.name}-${project.name}")
            description.set("The Convention of Composing Project.")
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

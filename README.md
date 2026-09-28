# Composing BuildLogic

The Gradle convention plugin that can be shared between Android and Multiplatform projects.

## Plugin List

### Settings

| ID | Description |
|---|---|
| `tw.boris4110799.composing.convention.settings` | Configure Convention Settings. |

### Convention

| ID | Description |
|---|---|
| `tw.boris4110799.composing.convention.androidCompose` | Configure Android Compose. |
| `tw.boris4110799.composing.convention.androidLibrary` | Configure Android Library. |
| `tw.boris4110799.composing.convention.androidMavenPublish` | Configure Android Maven Publish. |
| `tw.boris4110799.composing.convention.multiplatformCompose` | Configure Multiplatform Compose. |
| `tw.boris4110799.composing.convention.multiplatformLibrary` | Configure Multiplatform Library. |
| `tw.boris4110799.composing.convention.multiplatformMavenPublish` | Configure Multiplatform Maven Publish. |

## Setup

```kotlin
pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/boris4110799/ComposingBuildLogic")

            credentials {
                username = "" // Fill in with your name.
                password = "" // Fill in with your PAT.
            }
        }
    }
    plugins {
        // Convention Plugin
        id("tw.boris4110799.composing.convention.androidLibrary") version "1.0.0"
    }
}

// Convention Settings Plugin
plugins {
    id("tw.boris4110799.composing.convention.settings") version "1.0.0"
}

// Convention Settings
composing {
    basePackage = "com.example.app"
}
```

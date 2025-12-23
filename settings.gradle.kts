@file:Suppress("UnstableApiUsage")

rootProject.name = "apeiron"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    plugins {
        id("org.jetbrains.kotlin.jvm") version "2.2.21"
        id("org.jetbrains.kotlin.android") version "2.2.21"
        id("org.jetbrains.kotlin.multiplatform") version "2.2.21"
        id("org.jetbrains.kotlin.plugin.serialization") version "2.2.21"
        id("org.jetbrains.kotlin.plugin.compose") version "2.2.21"
        id("com.android.application") version "8.11.0"
        id("com.android.library") version "8.11.0"
        id("org.jetbrains.compose") version "1.6.11"
        id("com.google.devtools.ksp") version "2.2.21-2.0.4"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":packages:core")
include(":packages:client-webapi")
include(":packages:export")
include(":packages:infra-desktop")
include(":packages:infra-mobile")
include(":packages:infra-web")

include(":apps:desktop")
include(":apps:mobile")
include(":apps:server")
include(":apps:web")
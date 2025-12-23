plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose)        // org.jetbrains.compose
    alias(libs.plugins.kotlin.compose) // org.jetbrains.kotlin.plugin.compose
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(project(":packages:core"))

    implementation(project(":packages:infra-desktop"))
    implementation(project(":packages:client-webapi"))

    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.logback.classic)
}

compose.desktop {
    application {
        mainClass = "apeiron.desktop.DesktopMainKt"
    }
}

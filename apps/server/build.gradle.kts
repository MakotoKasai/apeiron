plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    application
}

dependencies {
    implementation(project(":packages:main"))
    implementation(project(":packages:client-webapi"))

    implementation(projects.packages.core)
    implementation(projects.packages.infraWeb)

    implementation(libs.bundles.ktor.server)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.logback.classic)
}

application {
    // あなたの server main の FQN に合わせて変更
    mainClass.set("apeiron.server.ServerMainKt")
}
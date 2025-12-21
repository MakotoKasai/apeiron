plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm()
    // androidTarget() や ios* は必要になったら足す

    sourceSets {
        commonMain.dependencies {
            implementation(projects.packages.core)
            implementation(libs.bundles.ktor.client.common)
            implementation(libs.ktor.serialization.kotlinx.json)
        }
    }
}
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "apeiron.infra.mobile"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }
}

dependencies {

    implementation(project(":packages:main"))
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}
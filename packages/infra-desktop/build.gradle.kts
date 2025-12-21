plugins {
    // JVM モジュールとして成立させる（これが無いと implementation が出ない）
    id("org.jetbrains.kotlin.jvm")
}

dependencies {

    implementation(project(":packages:main"))
    implementation(libs.bundles.exposed)
    implementation(libs.sqlite.jdbc)

    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)

    testImplementation(kotlin("test"))
    testImplementation(libs.bundles.tests.junit5)
}

tasks.test {
    useJUnitPlatform()
}
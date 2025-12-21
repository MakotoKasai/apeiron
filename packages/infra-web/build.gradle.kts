plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.packages.core)

    // DB（まずはSQLiteでOK。将来Postgresへ差し替え可能）
    implementation(libs.bundles.exposed)
    implementation(libs.hikari)
    implementation(libs.sqlite.jdbc)
    // implementation(libs.postgresql) // 将来

    implementation(libs.slf4j.api)

    testImplementation(libs.bundles.tests.junit5)
}

tasks.test {
    useJUnitPlatform()
}

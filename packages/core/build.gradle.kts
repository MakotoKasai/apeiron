plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    // 必要なら。Androidターゲットを持たない core なら jvm() で十分
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                // あなたの catalog に合わせて。無ければ一旦消してもOK
                implementation(libs.bundles.kotlinx.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
            }
        }
    }
}
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

val keysPropertiesFile: File = rootProject.file("keys.properties")
val keysProperties: Properties = Properties().apply {
    load(keysPropertiesFile.inputStream())
}
android {
    namespace = "com.example.core.data"
    compileSdk = 34

    defaultConfig {
        buildConfigField("String", "PUBLIC_KEY", keysProperties["PUBLIC_KEY"]?.toString().orEmpty())
        buildConfigField("String", "PRIVATE_KEY", keysProperties["PRIVATE_KEY"]?.toString().orEmpty())
        buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com:443/v1/public/\"")
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
}
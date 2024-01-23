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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(project(":core:model"))

    implementation("io.insert-koin:koin-android:3.5.3")

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    implementation("com.squareup.okhttp3:logging-interceptor")

    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
}
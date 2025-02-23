plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")
}

android {
    namespace = "com.mangabible"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mangabible"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }


    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")

    // Moshi Converter Factory (para Retrofit)
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // OkHttp (usado pelo Retrofit)
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // Logging Interceptor (OkHttp) - Opcional, mas recomendado
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Kotlin Reflect
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20")

    // ViewModel KTX (inclui viewModelScope)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation(libs.androidx.tracing.perfetto.handshake)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.storage)
    implementation(libs.androidx.material3.android)

    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // Compose
    implementation(libs.androidx.activity.compose)

    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")


    //kapt
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
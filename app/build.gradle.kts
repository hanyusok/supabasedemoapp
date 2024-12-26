plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.supabasedemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.supabasedemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //resValue
        resValue("string", "SUPABASE_URL", "http://martclinic.cafe24.com:8000")
        resValue("string", "ANON_KEY", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ewogICJyb2xlIjogImFub24iLAogICJpc3MiOiAic3VwYWJhc2UiLAogICJpYXQiOiAxNzMwOTkxNjAwLAogICJleHAiOiAxODg4NzU4MDAwCn0.lA6CORXNZ8FLfK3_Y0dVo7XgavbtrdOfNZh1ursbjQQ")
        resValue("string","SERVICE_ROLE_KEY", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ewogICJyb2xlIjogInNlcnZpY2Vfcm9sZSIsCiAgImlzcyI6ICJzdXBhYmFzZSIsCiAgImlhdCI6IDE3MzA5OTE2MDAsCiAgImV4cCI6IDE4ODg3NTgwMDAKfQ.Vq7dL2f0kjOL3jTOSbh4OQFySMcjZGK6v3U6Dr8jhSo")

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
        compose = true
    }
    kapt {
        correctErrorTypes = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //supabase
    implementation("io.github.jan-tennert.supabase:postgrest-kt:3.0.3")
    implementation("io.github.jan-tennert.supabase:storage-kt:3.0.3")
    implementation("io.github.jan-tennert.supabase:realtime-kt:3.0.3")
    implementation("io.github.jan-tennert.supabase:auth-kt:3.0.3")
    implementation("io.ktor:ktor-client-android:3.0.2")
    implementation("io.ktor:ktor-client-core:3.0.2")
    implementation("io.ktor:ktor-utils:3.0.2")

    //hilt dagger dependency
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // Other necessary dependencies (e.g., ViewModel support)
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0")


}
plugins {
    id ("kotlin-kapt")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinPlugin)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kspSymbolProcessing)
}

android {
    namespace = "com.example.cryptocurrencycompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cryptocurrencycompose"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    val hilt_version = "2.51.1"
    val viewmodel_version = "2.8.1"
    val hilt_compiler = "1.2.0"
    val room_version = "2.6.1"
    val ksp_version = "1.9.0-1.0.12"



    implementation (libs.androidx.lifecycle.runtime.compose)
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    // Dagger - Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)
    kapt (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.moshi)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    // Room
    implementation (libs.androidx.room.ktx)
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.paging)

    // Compose Nav Destinations
    implementation (libs.compose.destinations)
    ksp (libs.ksp)

    // KSP
    ksp(libs.symbol.processing.api)


    //Paging
    implementation (libs.androidx.paging.compose)

    //Charts
    implementation (libs.ycharts)

    //swipe refresh
    implementation (libs.accompanist.swiperefresh)

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
}
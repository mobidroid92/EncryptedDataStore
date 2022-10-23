plugins {
    id ("com.android.application")
    kotlin ("android")
}

android {
    namespace = "com.bdour.encrypteddatastore"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.bdour.encrypteddatastore"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation (project(":EncryptedDataStore"))
}
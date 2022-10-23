import Versions.HILT_ANDROID

plugins {
    id("com.android.library")
    kotlin ("android")
    kotlin ("kapt")
    id("maven-publish")
}

android {
    namespace = "com.bdour.encrypteddatastore"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    /**
     * DataStore
     * https://developer.android.com/jetpack/androidx/releases/datastore
     */
    api ("androidx.datastore:datastore-preferences:1.0.0")

    /**
     * Coroutine
     * https://github.com/Kotlin/kotlinx.coroutines/releases
     */
    val COROUTINE_VERSION = "1.6.4"
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINE_VERSION")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINE_VERSION")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINE_VERSION")

    testImplementation("junit:junit:4.13.2")

    /**
     * androidx-test
     * //https://developer.android.com/jetpack/androidx/releases/test
     */
    val ANDROIDX_TEST = "1.4.0"
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.3")
    androidTestImplementation("androidx.test:runner:$ANDROIDX_TEST")
    androidTestImplementation("androidx.test:rules:$ANDROIDX_TEST")

    //    androidTestImplementation ("androidx.arch.core:core-testing:2.1.0")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.mobidroid92"
            artifactId = "EncryptedDataStore"
            version = "1.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
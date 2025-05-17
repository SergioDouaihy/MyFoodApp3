plugins {
//    alias(libs.plugins.android.application)
    id("com.android.application") // Applies the Android application plugin
    id("com.google.gms.google-services") // Google services plugin, already applied, no need to apply again
}

android {
    namespace = "com.example.myfoodapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myfoodapp"
        minSdk = 24
        targetSdk = 35
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Dependency management with version catalog (libs)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Firebase dependencies
//    implementation(libs.firebase.database.v2030)
//    implementation(libs.google.firebase.analytics)
    implementation(libs.play.services.measurement.api)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.database)
    implementation(libs.androidx.navigation.ui)
    implementation ("com.squareup.picasso:picasso:2.8")

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation (libs.glide)  // or latest version
    annotationProcessor (libs.compiler)

    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
    implementation ("com.google.firebase:firebase-storage:20.3.0")
    implementation ("com.google.firebase:firebase-firestore")
    implementation ("com.google.firebase:firebase-database")
    implementation ("androidx.recyclerview:recyclerview:1.3.1")
    implementation ("com.google.firebase:firebase-firestore:24.9.1")

//    implementation(libs.appcompat.v161)
//    implementation(libs.com.google.firebase.firebase.analytics)
//    implementation(libs.firebase.database)
    implementation ("com.google.code.gson:gson:2.8.9")

}
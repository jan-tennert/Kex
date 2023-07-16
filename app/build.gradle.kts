import java.io.FileInputStream
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlinx.serialization)
}


val secretsFile = rootProject.file("secrets.properties");
val secretsProperties = Properties()
secretsProperties.load(FileInputStream(secretsFile))

android {
    namespace = "io.github.jan.kex"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.github.jan.kex"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "SUPABASE_URL", secretsProperties["SUPABASE_URL"] as String)
        buildConfigField("String", "SUPABASE_KEY", secretsProperties["SUPABASE_KEY"] as String)
        buildConfigField("String", "GOOGLE_CLIENT_ID", secretsProperties["GOOGLE_CLIENT_ID"] as String)
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0-dev-k1.9.0-6a60475e07f"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName = "io.github.jan.kex.data.local"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
   // implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.bundles.supabase)
    implementation(libs.bundles.sqldelight)
   // implementation(libs.compose.destinations)
    api(libs.compose.navigation)
    implementation(libs.bundles.koin)
    implementation(libs.compose.onetap)
  //  ksp(libs.compose.destinations.ksp)
    implementation(libs.swipe.refresh)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
 //   androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}
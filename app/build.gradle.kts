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
if(secretsFile.exists()) {
    secretsProperties.load(FileInputStream(secretsFile))
} else {
    //this is temporary, there should be a check for either the secrets file or env variables
    println("""
    No secrets.properties file found. Pattern:
    TODO
    """)
}

val keystoreFile = File("/home/runner/work/Kex/Kex/app/keystore/android_keystore.keystore")
val isCI = keystoreFile.exists()


android {
    namespace = "io.github.jan.kex"
    compileSdk = 34

    signingConfigs {
        if(isCI) {
            register("release") {
                storeFile = keystoreFile
                storePassword = System.getenv("SIGNING_STORE_PASSWORD")
                keyAlias = System.getenv("SIGNING_KEY_ALIAS")
                keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
            }
        }
    }
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

        buildConfigField("String", "SUPABASE_URL", (secretsProperties["SUPABASE_URL"] as? String) ?: "\"${System.getenv("SUPABASE_URL")}\"")
        buildConfigField("String", "SUPABASE_KEY", (secretsProperties["SUPABASE_KEY"] as? String) ?: "\"${System.getenv("SUPABASE_KEY")}\"")
        buildConfigField("String", "GOOGLE_CLIENT_ID", (secretsProperties["GOOGLE_CLIENT_ID"] as? String) ?: "\"${System.getenv("GOOGLE_CLIENT_ID")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
            if(isCI) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
        named("debug") {
            if(isCI) {
                signingConfig = signingConfigs.getByName("release")
            }
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
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
            excludes += "META-INF/*.kotlin_module"
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
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.activity.compose)
   // implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.material3.windowsize)
    implementation(libs.bundles.supabase)
    implementation(libs.bundles.sqldelight)
   // implementation(libs.compose.destinations)
    api(libs.compose.navigation)
    implementation(libs.bundles.koin)
    implementation(libs.compose.onetap)
    implementation(libs.compose.rich.editor)
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
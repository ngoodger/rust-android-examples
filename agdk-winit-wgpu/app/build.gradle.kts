plugins {
    id("org.mozilla.rust-android-gradle.rust-android")
    id("com.android.application")
    id("kotlin-android")
}
android {
    //ndkVersion= "25.2.9519653"
    compileSdk =33

    defaultConfig {
        applicationId= "co.realfit.agdkwinitwgpu"
        minSdk= 28
        targetSdk =33
        versionCode= 1
        versionName= "1.0"

        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
    }
cargo {
  val ndkDir ="/data/data/com.termux/files/home/android-ndk-r26b"
        prebuiltToolchains = true
        module  = "../rust"       // Or whatever directory contains your Cargo.toml
        libname = "main"          // Or whatever matches Cargo.toml's [package] name.
        targets = listOf("arm", "arm64" )  // See bellow for a longer list of options
        //environment.set("AR", "llvm-ar")
          }


    
    tasks.whenTaskAdded {
        if((name == "javaPreCompileDebug" || name == "javaPreCompileRelease")) {
            dependsOn("cargoBuild")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled= false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled= false
            //packagingOptions {
            //    doNotStrip '**/*.so'
            //}
            //debuggable true
        }
    }
    compileOptions {
        sourceCompatibility= JavaVersion.VERSION_1_8
        targetCompatibility= JavaVersion.VERSION_1_8
    }
    namespace= "co.realfit.agdkwinitwgpu"
}

dependencies {
   implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
implementation("com.google.android.gms:play-services-games:21.0.0")
implementation("androidx.games:games-controller:2.0.2")
implementation ("androidx.games:games-activity:1.1.0")

}


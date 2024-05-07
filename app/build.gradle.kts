plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.android_traing"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.android_traing"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
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
    implementation(libs.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.github.AppIntro:AppIntro:6.3.1") // 闪屏页
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("io.github.scwang90:refresh-layout-kernel:2.1.0")      //核心必须依赖
    implementation("io.github.scwang90:refresh-header-classics:2.1.0")    //经典刷新头
    implementation("io.github.scwang90:refresh-footer-ball:2.1.0")        //球脉冲加载
    implementation("io.github.scwang90:refresh-footer-classics:2.1.0")    //经典加载
    implementation("io.github.scwang90:refresh-header-material:2.1.0")

    implementation("com.squareup.retrofit2:retrofit:2.11.0") // 网络框架
    implementation("com.squareup.retrofit2:converter-gson:2.11.0") // json转换器
    implementation("com.github.leonardoxh:retrofit2-livedata-adapter:1.1.2") // livedata适配器

    // Adapter适配器
    implementation("io.github.cymchad:BaseRecyclerViewAdapterHelper:3.0.14") // RecyclerAdapter
    // 轮播图
    implementation("io.github.youth5201314:banner:2.2.3")
    // web
    implementation("io.github.justson:agentweb-core:v5.1.1-androidx")

    // 爆炸菜单
    implementation("com.nightonke:boommenu:2.1.1")
    // 图标
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    // 视频播放器
    implementation("com.github.CarGuo.GSYVideoPlayer:GSYVideoPlayer:v8.6.0-release-jitpack")

    implementation ("androidx.multidex:multidex:2.0.1")

    // 圆形图片
    implementation("de.hdodenhof:circleimageview:3.1.0")

    //  bmob
    implementation ("io.github.bmob:android-sdk:3.9.4")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.8")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("com.squareup.okhttp3:okhttp:4.8.1")
    implementation ("com.squareup.okio:okio:2.2.2")
    implementation ("com.google.code.gson:gson:2.8.5")

}

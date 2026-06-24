import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.7.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.25")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    plugins.withId("com.android.application") {
        extensions.configure<BaseAppModuleExtension> {
            namespace = "com.kakao.talk.theme.apeach"
            compileSdk = 35
            buildToolsVersion = "35.0.0"
            defaultConfig {
                minSdk = 28
                targetSdk = 35
                versionName = "1.0.0"
                // CI에서 -PbuildNumber=123 으로 넘기면 그 값을 쓰고, 없으면 기존처럼 100
                versionCode = (project.findProperty("buildNumber") as String?)?.toIntOrNull() ?: 100
                // CI에서 -PappId=com.kakao.talk.theme.b123 으로 넘기면 그 값을 쓰고, 없으면 기존 템플릿 ID
                applicationId = (project.findProperty("appId") as String?) ?: "com.kakao.talk.theme.template"
                sourceSets {
                    getByName("main") {
                        res.srcDirs("src/main/res", "src/main/theme", "src/main/theme-adv")
                    }
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }
            buildFeatures {
                viewBinding = true
            }
            dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.25")
            dependencies.add("implementation", "androidx.annotation:annotation:1.9.1")
        }
    }
}

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

val kotlinVersion = "1.5.21"
val gradleVersion = "7.0.0"

gradlePlugin {
    plugins {
        create("App Plugin") {
            id = "com.vaishnavi.test.app"
            //implementationClass = "TestAppPlugin"
        }

        create("Library Plugin") {
            id = "com.vaishnavi.test.library"
            //implementationClass = "LibraryPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:$gradleVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation(gradleApi())
    implementation(localGroovy())
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs = listOf(
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlin.Experimental"
    )
}
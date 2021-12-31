plugins {
    kotlin("jvm") version "1.6.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":lib"))
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")
}

application {
    mainClass.set("com.pucrs.sds.app.ApplicationKt")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.allWarningsAsErrors = true
}
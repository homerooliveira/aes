plugins {
    kotlin("jvm")
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
    mainClass.set("com.pucrs.sds.cli.ApplicationKt")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.allWarningsAsErrors = true
}
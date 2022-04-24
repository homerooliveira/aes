import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("commons-codec:commons-codec:1.15")

    testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.allWarningsAsErrors = true
}
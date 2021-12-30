import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("commons-codec:commons-codec:1.15")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.pucrs.sds.ApplicationKt")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.allWarningsAsErrors = true
}
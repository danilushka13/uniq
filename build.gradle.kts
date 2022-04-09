import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "me.dedia"
version = "2.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    implementation( "org.jetbrains.kotlin:kotlin-stdlib")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")

}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "UniqKt"
    }
   configurations["compileClasspath"].forEach { file: File ->
       from(zipTree(file.absoluteFile))
   }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}


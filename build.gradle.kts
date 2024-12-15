plugins {
    kotlin("jvm") version "2.0.21"
}

group = "de.schulwegwarnung"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("io.javalin:javalin:6.3.0")

    // TODO: Custom Prometheus implementation
    implementation("org.slf4j:slf4j-simple:2.0.16")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
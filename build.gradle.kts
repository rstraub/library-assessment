plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("io.kotest:kotest-runner-junit5:4.3.1")
    testImplementation("io.kotest:kotest-assertions-core:4.3.1")
    testImplementation("io.kotest:kotest-property:4.3.1")
}

application {
    mainClass.set("nl.rstraub.kata.kotlin.args.ArgsKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

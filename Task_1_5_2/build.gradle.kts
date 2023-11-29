plugins {
    id("java")
    id("jacoco")
}

group = "ru.nsu.chudinov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
    implementation("com.fasterxml.jackson.core:jackson-core:2.16.0")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.16.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.0")

// https://mvnrepository.com/artifact/args4j/args4j
    implementation("args4j:args4j:2.33")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    "jacocoTestReport"(JacocoReport::class) {
        reports {
            xml.required.set(true)
            html.required.set(true)
            html.outputLocation.set(layout.buildDirectory.dir("$buildDir/jacoco/jacocoHtml"))
        }
    }
}
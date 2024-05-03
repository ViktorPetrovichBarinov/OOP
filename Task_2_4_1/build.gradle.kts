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

    implementation(group = "org.codehaus.groovy", name = "groovy-all", version = "3.0.9")
    implementation(group = "org.eclipse.jgit", name = "org.eclipse.jgit", version = "5.11.0.202103091610-r")
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
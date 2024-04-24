plugins {
    id("java")
}

group = "itmo.anastasiya"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":lab-2:service"))
    implementation(project(":lab-2:model"))
    implementation("info.picocli:picocli:4.7.5")
}

tasks.test {
    useJUnitPlatform()
}
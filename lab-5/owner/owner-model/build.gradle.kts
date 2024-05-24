plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "itmo.anastasiya"
version = "1.0-SNAPSHOT"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
springBoot {
    mainClass.value("itmo.Anastasiya.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-security:3.2.4")
    implementation("org.springframework:spring-tx:6.1.6")
    implementation("org.springframework.kafka:spring-kafka:3.1.1")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.2.4")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
}

tasks.test {
    useJUnitPlatform()
}

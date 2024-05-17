plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
}

group = "ru.medoedoed"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation( "org.springframework.boot:spring-boot-starter-validation:3.2.4")
}

tasks.test {
    useJUnitPlatform()
}
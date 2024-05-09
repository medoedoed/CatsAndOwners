plugins {
    id("java")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.medoedoed"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":services"))
    implementation(project(":dao"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation( "org.springframework.boot:spring-boot-starter-validation:3.2.4")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

    implementation("org.postgresql:postgresql:42.7.3")

    implementation("io.swagger.core.v3:swagger-annotations:2.2.21")
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")

    implementation("org.springframework.boot:spring-boot-starter-security:3.2.4")

}

tasks.test {
    useJUnitPlatform()
}
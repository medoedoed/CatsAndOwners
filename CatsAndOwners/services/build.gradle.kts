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
//    implementation(project(":dao"))
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//
//    compileOnly("org.projectlombok:lombok:1.18.30")
//    annotationProcessor("org.projectlombok:lombok:1.18.30")
//    implementation("io.jsonwebtoken:jjwt:0.2")
//
//    testCompileOnly("org.projectlombok:lombok:1.18.30")
//    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
//
//
//    implementation("org.springframework.security:spring-security-web:6.2.3")
//    implementation("org.springframework.security:spring-security-config:6.2.3")
//    implementation("org.springframework.security:spring-security-core:6.2.4")
//
//    implementation("org.apache.commons:commons-lang3:3.14.0")
//    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
//
//
//    implementation( "org.springframework.boot:spring-boot-starter-validation:3.2.4")
//
//
//    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
//    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
//    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
//
//    implementation("org.springframework.boot:spring-boot-starter-security:3.2.4")

    implementation(project(":dao"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    //Lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

    // Validation
    implementation( "org.springframework.boot:spring-boot-starter-validation:3.2.4")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security:3.2.4")
}

tasks.test {
    useJUnitPlatform()
}
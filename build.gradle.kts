plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.2.1" // Updated Spring Boot plugin version
    id("io.spring.dependency-management") version "1.1.4" //  Matching dependency management version for 3.2.1
    id("pmd") // Add PMD plugin
}

group = "id.ac.ui.cs.advprog"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    sourceSets {
        main {
            java {
                srcDirs("src/main/java")
            }
            resources {
                srcDirs("src/main/resources")
            }
        }
        test {
            java {
                srcDirs("src/test/java")
            }
            resources {
                srcDirs("src/test/resources")
            }
        }
    }
}

repositories {
    mavenCentral()
}

val seleniumJavaVersion = "4.14.1"
val webdrivermanagerVersion = "5.6.3"
val junitJupiterVersion = "5.9.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf") // Add thymeleaf back if you need it

    testImplementation(platform("org.junit:junit-bom:$junitJupiterVersion")) // JUnit BOM for version management (Tutorial 2)
    testImplementation("org.springframework.boot:spring-boot-starter-test") // Spring Boot Test
    testImplementation("org.junit.jupiter:junit-jupiter-api") // JUnit Jupiter API (no version - BOM managed)
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine") // JUnit Jupiter Engine (no version - BOM managed)

    testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumJavaVersion")
    testImplementation("io.github.bonigarcia:webdrivermanager:$webdrivermanagerVersion")
    testImplementation("io.github.bonigarcia:selenium-jupiter:5.0.1") { // Explicit selenium-jupiter version (Tutorial 2)
        exclude(group = "org.junit.jupiter") // Exclude JUnit Jupiter from selenium-jupiter (Tutorial 2)
    }

    compileOnly("org.projectlombok:lombok:1.18.30") // Explicit Lombok version (Tutorial 2)
    annotationProcessor("org.projectlombok:lombok:1.18.30") // Explicit Lombok version (Tutorial 2)
    testCompileOnly("org.projectlombok:lombok:1.18.30") // Explicit Lombok version (Tutorial 2)
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30") // Explicit Lombok version (Tutorial 2)


    developmentOnly("org.springframework.boot:spring-boot-devtools") // Keep devtools if needed
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor") // Keep config processor if needed
}

tasks.register<Test>("unitTest") {
    description = "Runs unit tests."
    group = "verification"
    filter {
        excludeTestsMatching("*FunctionalTest")
    }
}

tasks.register<Test>("functionalTest") {
    description = "Runs functional tests."
    group = "verification"
    filter {
        includeTestsMatching("*FunctionalTest")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.test {
    filter {
        excludeTestsMatching("*FunctionalTest")
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

// PMD Configuration "To run ./gradlew clean pmdMain pmdTest"
pmd {
    toolVersion = "7.0.0-rc4"
    isConsoleOutput = true
    ruleSets = listOf(
        "category/java/bestpractices.xml",
        "category/java/codestyle.xml",
        "category/java/design.xml",
        "category/java/errorprone.xml",
        "category/java/multithreading.xml",
        "category/java/performance.xml",
        "category/java/security.xml"
    )
}
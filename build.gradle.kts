import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.2.7.RELEASE"
  id("io.spring.dependency-management") version "1.0.9.RELEASE"
  kotlin("jvm") version "1.3.71"
  kotlin("plugin.spring") version "1.3.71"
  id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly: Configuration by configurations.creating
configurations {
  runtimeClasspath {
    extendsFrom(developmentOnly)
  }
}

repositories {
  jcenter()
  mavenCentral()
  maven {
    name = "confluent"
    url = uri("http://packages.confluent.io/maven/")
  }
}

val springCloudVersion = "Hoxton.SR4"

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    mavenBom("org.apache.camel.springboot:camel-spring-boot-dependencies:3.2.0")
  }
}

// ext["thymeleaf.version"] = "3.0.11.RELEASE"
// ext["thymeleaf-layout-dialect.version"] = "2.4.1"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
  implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")

  implementation("org.springframework.cloud:spring-cloud-starter-config")
  implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
  implementation("org.apache.camel.springboot:camel-kafka-starter")
  implementation("org.apache.camel:camel-endpointdsl")
  implementation("org.apache.avro:avro:1.9.2")
  implementation("io.confluent:kafka-avro-serializer:5.5.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

  developmentOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
  testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

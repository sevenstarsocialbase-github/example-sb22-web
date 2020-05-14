import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.2.7.RELEASE"
  id("io.spring.dependency-management") version "1.0.9.RELEASE"
  kotlin("jvm") version "1.3.71"
  kotlin("plugin.spring") version "1.3.71"
  id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
  id("com.commercehub.gradle.plugin.avro") version "0.9.1"
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
  dependencies {
    dependencySet("io.kotlintest:3.4.2") {
      entry("kotlintest-runner-junit5")
      entry("kotlintest-extensions-spring")
    }
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
  implementation("org.apache.camel:camel-http")
  implementation("org.apache.avro:avro:1.9.2")
  implementation("io.confluent:kafka-avro-serializer:5.5.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
//  runtimeOnly("org.codehaus.groovy:groovy:2.5.1")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    exclude(module = "mockito-core")
  }
  testImplementation("com.ninja-squad:springmockk:2.0.1")
  testImplementation("io.kotlintest:kotlintest-runner-junit5")
  testImplementation("io.kotlintest:kotlintest-extensions-spring")
}

avro {
//  setCreateSetters("false")
  fieldVisibility = "PRIVATE"
  outputCharacterEncoding = "UTF-8"
}

val generateAvro = tasks.register("generateAvro", com.commercehub.gradle.plugin.avro.GenerateAvroJavaTask::class.java) {
  source("src/main/resources/avro")
  setOutputDir(file("src/main/java"))
}

tasks.named("compileJava").configure {
  dependsOn(generateAvro)
}

val generateProtocol = tasks.register("generateProtocol", com.commercehub.gradle.plugin.avro.GenerateAvroProtocolTask::class.java) {
  source(file("src/main/avro"))
  include("**/*.avdl")
  setOutputDir(file("build/generated-avro-main-avpr"))
}

tasks.register("generateSchema", com.commercehub.gradle.plugin.avro.GenerateAvroProtocolTask::class.java) {
  dependsOn(generateProtocol)
  source(file("src/main/avro"))
  source(file("build/generated-avro-main-avpr"))
  include("**/*.avpr")
  setOutputDir(file("build/generated-main-avro-avsc"))
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

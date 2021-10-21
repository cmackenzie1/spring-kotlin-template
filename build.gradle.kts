import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.21"
	kotlin("kapt") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	idea
	jacoco
}

group = "io.github.cmackenzie1"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

idea {
    module {
        sourceDirs.plusAssign(files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main"))
        generatedSourceDirs.plusAssign(
            files(
                "build/generated/source/kapt/main",
                "build/generated/source/kaptKotlin/main"
            )
        )
    }
}

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation(platform("io.strikt:strikt-bom:0.28.1"))
    testImplementation("com.ninja-squad:springmockk:2.0.3")
    testImplementation("io.strikt:strikt-core")
    testImplementation("io.strikt:strikt-jackson")
    testImplementation("io.strikt:strikt-spring")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

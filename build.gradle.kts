plugins {
	id("java")
	id("org.springframework.boot") version "3.4.2"
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}


allprojects {
	group = "com.fc"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

}

subprojects {
	apply {
		plugin("java")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
		plugin("java-library")
	}

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(21)
		}
	}


	dependencies {
		testImplementation(platform("org.junit:junit-bom:5.10.0"))
		testImplementation("org.junit.jupiter:junit-jupiter")
	}

	tasks.test {
		useJUnitPlatform()
	}
}

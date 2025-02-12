dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.testcontainers:testcontainers:1.19.8")
    api("org.springframework.cloud:spring-cloud-starter-stream-kafka")
}
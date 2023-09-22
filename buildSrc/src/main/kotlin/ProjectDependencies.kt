object LibVers {
    const val spring_boot = "3.1.4"
    const val junit = "5.10.0"
    const val jackson = "2.15.2"
    const val slf4j = "2.0.9"
    const val postgresql = "42.6.0"
    const val flyway = "9.22.2"
}

object Libs {
    // Kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Global.kotlin_version}"
    const val kotlin_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Global.kotlin_version}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Global.kotlin_version}"

    // Jackson
    const val jackson_kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${LibVers.jackson}"
    const val jackson_databind = "com.fasterxml.jackson.core:jackson-databind:${LibVers.jackson}"

    // Spring
    const val spring_boot_starter = "org.springframework.boot:spring-boot-starter:${LibVers.spring_boot}"
    const val spring_boot_starter_web = "org.springframework.boot:spring-boot-starter-web:${LibVers.spring_boot}"
    const val spring_boot_starter_data_jdbc = "org.springframework.boot:spring-boot-starter-data-jdbc:${LibVers.spring_boot}"
    const val spring_boot_starter_logging = "org.springframework.boot:spring-boot-starter-logging:${
        LibVers.spring_boot
    }"
    const val spring_boot_starter_test = "org.springframework.boot:spring-boot-starter-test:${LibVers.spring_boot}"

    // Logging
    const val slf4j_api = "org.slf4j:slf4j-api:${LibVers.slf4j}"

    // Tests
    const val junit_params = "org.junit.jupiter:junit-jupiter-params:${LibVers.junit}"
    const val junit_engine = "org.junit.jupiter:junit-jupiter-engine:${LibVers.junit}"

    // Database
    const val postgresql = "org.postgresql:postgresql:${LibVers.postgresql}"
    const val flyway = "org.flywaydb:flyway-core:${LibVers.flyway}"
}

object PluginVers {
    const val kotlin = Global.kotlin_version
    const val spring_boot = LibVers.spring_boot
    const val detekt = "1.19.0"
    const val detekt_formatting = detekt
    const val spring_dependency_management = "3.1.4"
    const val spring_kotlin = Global.kotlin_version
    const val owasp_dependencies = "8.2.1"
}

object Plugins {
    const val kotlin = "org.jetbrains.kotlin.jvm"
    const val spring_boot = "org.springframework.boot"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val detekt_formatting = "io.gitlab.arturbosch.detekt:detekt-formatting"
    const val spring_dependency_management = "io.spring.dependency-management"
    const val spring_kotlin = "org.jetbrains.kotlin.plugin.spring"
    const val owasp_dependencies = "org.owasp.dependencycheck"
}
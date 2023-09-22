val parentProjectDir = projectDir

plugins {
    id(Plugins.kotlin) version PluginVers.kotlin apply false
    id(Plugins.detekt) version PluginVers.detekt
    id(Plugins.owasp_dependencies) version PluginVers.owasp_dependencies
}

subprojects {
    configurations.all {
        resolutionStrategy {
            eachDependency {
                requested.version?.contains("snapshot", true)?.let {
                    if (it) {
                        throw GradleException("Snapshot found: ${requested.name} ${requested.version}")
                    }
                }
            }
        }
    }

    apply {
        plugin("java")
        plugin(Plugins.kotlin)
        plugin(Plugins.detekt)
        plugin("jacoco")
        plugin(Plugins.owasp_dependencies)
    }

    repositories {
        mavenCentral()
        mavenLocal()
    }

    detekt {
        config = files("$parentProjectDir/tools/detekt/detekt-config.yml")
        buildUponDefaultConfig = true
        source = files("src/main/kotlin", "src/test/kotlin", "src/test/gatling")

        dependencies {
            detektPlugins("${Plugins.detekt_formatting}:${PluginVers.detekt_formatting}")
        }
    }

    tasks {

        val check = named<DefaultTask>("check")

        val jacocoTestReport = named<JacocoReport>("jacocoTestReport")
        val jacocoTestCoverageVerification = named<JacocoCoverageVerification>("jacocoTestCoverageVerification")

        check {
            finalizedBy(jacocoTestReport)
        }

        jacocoTestReport {
            dependsOn(check)
            finalizedBy(jacocoTestCoverageVerification)
        }

        jacocoTestCoverageVerification {
            dependsOn(jacocoTestReport)

            violationRules {

                rule {
                    excludes = listOf("application", "telnet", "mock-server")
                    limit {
                        minimum = BigDecimal("0.9")
                    }
                }
            }
        }

        val failOnWarning = project.properties["allWarningsAsErrors"] != null && project
            .properties["allWarningsAsErrors"] == "true"

        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
                allWarningsAsErrors = failOnWarning
                freeCompilerArgs = listOf("-Xjvm-default=all-compatibility")
            }
        }

        withType<JavaCompile> {
            options.compilerArgs.add("-Xlint:all")
            sourceCompatibility = JavaVersion.VERSION_17.toString()
            targetCompatibility = JavaVersion.VERSION_17.toString()
        }

        withType<Test> {
            useJUnitPlatform()

            testLogging {
                events(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                )
                showStandardStreams = true
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            }

            systemProperties["pact.rootDir"] = layout.buildDirectory.dir("pacts")
        }
    }
}

configurations.all {
    resolutionStrategy {
        failOnNonReproducibleResolution()
    }
}
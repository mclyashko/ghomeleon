dependencies {
    implementation(project(":domain"))

    // kotlin
    implementation(Libs.kotlin_jdk8)
    implementation(Libs.kotlin_reflect)
    implementation(Libs.kotlin_stdlib)

    // spring
    implementation(Libs.spring_boot_starter_data_jdbc)
    runtimeOnly(Libs.postgresql)

    // tests
    testImplementation(Libs.junit_params)
    testRuntimeOnly(Libs.junit_engine)
}

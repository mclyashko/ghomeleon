dependencies {
    implementation(project(":domain"))

    // spring
    implementation(Libs.spring_boot_starter_data_jdbc)
    runtimeOnly(Libs.postgresql)

    // tests
    testImplementation(Libs.junit_params)
    testRuntimeOnly(Libs.junit_engine)
}

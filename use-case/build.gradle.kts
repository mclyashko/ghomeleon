dependencies {
    implementation(project(":domain"))

    // spring
    implementation(Libs.spring_boot_starter)

    // tests
    testImplementation(Libs.junit_params)
    testRuntimeOnly(Libs.junit_engine)
}

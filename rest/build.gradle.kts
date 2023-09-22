dependencies {
    implementation(project(":use-case"))
    implementation(project(":domain"))

    // spring
    implementation(Libs.spring_boot_starter_web)

    // tests
    testImplementation(Libs.junit_params)
    testRuntimeOnly(Libs.junit_engine)
}

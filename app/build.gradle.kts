plugins {
    id(Plugins.spring_boot) version PluginVers.spring_boot
    id(Plugins.spring_kotlin) version PluginVers.spring_kotlin
}

dependencies {
    implementation(project(":rest"))
    implementation(project(":use-case"))
    implementation(project(":domain"))
    implementation(project(":db"))

    // kotlin
    implementation(Libs.kotlin_jdk8)
    implementation(Libs.kotlin_reflect)
    implementation(Libs.kotlin_stdlib)

    // spring
    implementation(Libs.spring_boot_starter)

    // tests
    testImplementation(Libs.junit_params)
    testRuntimeOnly(Libs.junit_engine)
}

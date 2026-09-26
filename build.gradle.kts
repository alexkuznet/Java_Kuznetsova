plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.rest-assured:rest-assured:6.0.1")
    // Source: https://mvnrepository.com/artifact/io.rest-assured/json-path
    implementation("io.rest-assured:json-path:6.0.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("printText") {
    group = "Stest"
    dependsOn("test")
    doLast()
    {
        println("Test run is over")
    }

}

//tasks.test {
//    useJUnitPlatform()
//}

tasks.register<Test>("smokeTest") {
group = "Stest"
useJUnitPlatform {

}
}

//tasks.register<Test>("smokeTest") {
//group = "Stest"
//useJUnitPlatform {
//includeTags("smoke")
//}
//}
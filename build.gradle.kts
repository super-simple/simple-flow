plugins {
    id("java")
    id("idea")
}

group = "org.ss"
version = "1.0.0-SNAPSHOT"


allprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "java-library")

    repositories {
        mavenCentral()
    }

    idea {
        module {
            isDownloadJavadoc = false
            isDownloadSources = false
        }
    }

}

subprojects {

    dependencies {
        implementation("org.jetbrains:annotations:24.0.1")
    }
}


subprojects {

    //exclude project simpleflow-core
    if (name != "simpleflow-core") {

        dependencies {
            testImplementation("org.junit.jupiter:junit-jupiter-api:${property("junit_version")}")
            testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${property("junit_version")}")
        }

        tasks.getByName<Test>("test") {
            useJUnitPlatform()
        }

    }

}
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
        maven {
            url = uri("https://maven.aliyun.com/repository/public/")
        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
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

    }
}
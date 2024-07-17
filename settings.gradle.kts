pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.aliyun.com/repository/gradle-plugin")
        }
    }
}

rootProject.name = "simple-flow"

include("simpleflow-core")
include("simpleflow-core-impl")
include("simpleflow-common")
include("simpleflow-example")
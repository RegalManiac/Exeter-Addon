plugins {
    id("net.fabricmc.fabric-loom") version "1.16.3"
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

base {
    archivesName = properties["archives_base_name"] as String
    group = properties["maven_group"] as String
    version = properties["version"] as String
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

repositories {
    maven {
        url = uri("https://jm.gserv.me/repository/maven-public/")
        content {
            includeGroup("info.journeymap")
        }
    }
    maven {
        url = uri("https://api.modrinth.com/maven/")
        content {
            includeGroup("maven.modrinth")
        }
    }
    maven { url = uri("https://www.cursemaven.com") }
    maven { url = uri("https://masa.dy.fi/maven") }
    // Meteor Client
    maven {
        name = "meteor-maven"
        url = uri("https://maven.meteordev.org/releases")
    }
    maven {
        name = "meteor-maven-snapshots"
        url = uri("https://maven.meteordev.org/snapshots")
    }
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    // Minecraft
    minecraft("com.mojang:minecraft:${properties["minecraft_version"] as String}")

    // Fabric
    implementation("net.fabricmc:fabric-loader:${properties["loader_version"] as String}")
    implementation("net.fabricmc.fabric-api:fabric-api:${properties["fabric_version"] as String}")

    // Meteor Client
    implementation("meteordevelopment:meteor-client:${properties["minecraft_version"] as String}-SNAPSHOT")
    compileOnly("meteordevelopment:baritone:${properties["minecraft_version"] as String}-SNAPSHOT")
    implementation("org.meteordev:starscript:0.2.5")
    implementation("meteordevelopment:orbit:0.2.4")
}

// loom {
//     accessWidenerPath = file("src/main/resources/sindiaddon-pro.accesswidener")
// }

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks.processResources {
    val replaceProperties = mapOf(
        "version" to properties["version"] as String,
        "minecraft_version" to properties["minecraft_version"] as String
    )
    inputs.properties(replaceProperties)

    filesMatching("fabric.mod.json") {
        expand(replaceProperties)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}
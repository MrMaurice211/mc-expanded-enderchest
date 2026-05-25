pluginManagement {
	repositories {
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
        gradlePluginPortal()
        maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
        maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.9.4"
}

stonecutter {

	create(rootProject) {
        versions("1.20", "1.20.5", "1.21.6").buildscript("build.gradle.kts")
        versions("26.1.2").buildscript("modern-build.gradle.kts")
	}

}

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")

}



group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}


kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.bytedeco:javacv:1.5.7")
                implementation("org.bytedeco:javacv-platform:1.5.7")
                implementation("uk.co.caprica:vlcj:4.8.2")
                implementation("uk.co.caprica:vlcj-natives:4.8.1")
                implementation("com.github.axet.play:libvlc:2.0.6-2")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "final"
            packageVersion = "1.0.0"
        }
    }
}

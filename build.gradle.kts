plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0" // Para o Shade Plugin do Maven

}

group = "com.br.gabrielmartins"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.dmulloy2.net/repository/public/") }
    maven { url = uri("https://mvnrepository.com/artifact/com.zaxxer/HikariCP") }
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
}

dependencies {
    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("com.google.zxing:core:3.5.2")
    implementation("net.dv8tion:JDA:5.0.0-beta.13")
    implementation("com.google.zxing:javase:3.3.0")
    implementation("com.github.HenryFabio:inventory-api:2.0.3")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("org.projectlombok:lombok:1.18.28")

    compileOnly(files("F:\\API\\PlaceholderAPI-2.11.6.jar"))
    compileOnly(files("F:\\API\\carbonspigot.jar"))
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.br.gabrielmartins.BukkitMain"
    }
}

tasks.shadowJar {
    archiveBaseName.set("Payments")
    archiveVersion.set("0.1.3-BETA")
    mergeServiceFiles()
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  kotlin("jvm") version "1.3.71"
  id("java")
  id("com.github.johnrengelman.shadow") version "5.2.0"
  id("application")
  id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "de.saring"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val javafxVersion = "11.0.2"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.thymeleaf:thymeleaf:3.0.11.RELEASE")
  implementation("org.slf4j:slf4j-log4j12:1.7.30")
  implementation("com.google.guava:guava:29.0-jre")
  implementation("com.atilika.kuromoji:kuromoji-ipadic:0.9.0")
  implementation("org.openjfx:javafx-controls:14")
  //implementation("no.tornado:tornadofx:1.7.17")
  implementation("no.tornado:tornadofx:1.7.19")  
  compileOnly("org.openjfx:javafx-graphics:$javafxVersion.version:win")
  compileOnly("org.openjfx:javafx-graphics:$javafxVersion:linux")
  compileOnly("org.openjfx:javafx-graphics:$javafxVersion:mac")

  testImplementation("org.mockito:mockito-junit-jupiter:3.3.3")
  testImplementation("org.junit.jupiter:junit-jupiter-engine:5.6.2")
  
  
  
  
	//implementation(kotlin("stdlib"))
	
	implementation("javax.inject:javax.inject:1")
	implementation("com.google.inject:guice:4.2.2:no_aop")
	
	implementation(platform("com.fasterxml.jackson:jackson-bom:2.10.0"))
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	
	//implementation("org.openjfx:javafx-controls:13")
	//implementation("no.tornado:tornadofx:1.7.19")  
  
  
  
  
  
  //      implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.70'
  //      implementation 'org.openjfx:javafx-fxml:14'
        implementation 'javax.inject:javax.inject:1'
        implementation 'eu.lestard:easy-di:0.3.0'
        implementation 'org.jdom:jdom2:2.0.6'
        implementation 'org.jfree:jfreechart-fx:1.0.1'
     //   implementation 'org.controlsfx:controlsfx:11.0.0'
  //      testImplementation 'org.junit.jupiter:junit-jupiter-api:5.4.0'
   //     testImplementation 'org.junit.jupiter:junit-jupiter-engine:5.4.0'
   //     testImplementation 'org.mockito:mockito-core:2.23.0'  
  
  
  
  
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf(
      "-Xjsr305=strict",
      "-Xopt-in=kotlin.ExperimentalUnsignedTypes"
    )
    jvmTarget = "11"
  }
}

tasks {
  named<ShadowJar>("shadowJar") {
    archiveBaseName.set("kotlin_tornadofx")
    mergeServiceFiles()
    manifest {
      attributes(mapOf("Main-Class" to "com.github.csolem.gradle.shadow.kotlin.example.App"))
    }
  }
}

tasks {
  build {
    dependsOn(shadowJar)
  }
}

javafx {
  version = javafxVersion
  modules = listOf("javafx.controls", "javafx.graphics", "javafx.fxml")
}

application {
  mainClassName = "de.saring.sportstracker.STMain"
}

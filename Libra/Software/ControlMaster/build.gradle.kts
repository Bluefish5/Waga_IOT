buildscript {
    val agp_version3 by extra("8.5.2")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}
val compileSdkVersion by extra(35)

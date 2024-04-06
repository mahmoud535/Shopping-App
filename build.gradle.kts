// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.google.hilt) apply false
//    classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2"

}

buildscript {
    repositories {
        google() // Add Google Maven repository if not already added
        // other repositories if any
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}
# Communi 🤖
An Application for the community built by the community


## What is inside the project 🎨
- **100% Kotlin-only**.
- 100% Gradle Kotlin DSL setup.
- Dependency versions managed via `buildSrc`.
- Kotlin Static Analysis via `ktlint` and `detekt`.
- Issues Template (bug report + feature request)
- Pull Request Template.

## Gradle Setup 🐘

This template is using [**Gradle Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) as well as the [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) to setup the build.

Dependencies are centralized inside the [Dependencies.kt](buildSrc/src/main/java/Dependencies.kt) file in the `buildSrc` folder. This provides convenient auto-completion when writing your gradle files.

## Naming convensions
Please check and read this [guideline](https://github.com/LintSchool/Communi-Android/wiki/Naming-Conventions)

## GitFlow 
We follow this [GitFlow guideline](https://datasift.github.io/gitflow/IntroducingGitFlow.html)

## We Use Git Emojis 
To make our git commits more enjoyable and useful we use [GitMoji](https://gitmoji.carloscuesta.me/).
It a source of Emojis to include in your commit message , Find you suits your commit most and add your commit message + the emoji code name from the website.
It's also helpful to indicate commit purpose, and pays off when we want to filter commits that for example related to performance only.

## Static Analysis 🔍

This template is using [**ktlint**](https://github.com/pinterest/ktlint) with the [ktlint-gradle](https://github.com/jlleitschuh/ktlint-gradle) plugin to format your code. To reformat all the source code as well as the buildscript you can run the `ktlintFormat` gradle task.

This template is also using [**detekt**](https://github.com/arturbosch/detekt) to analyze the source code, with the configuration that is stored in the [detekt.yml](config/detekt/detekt.yml) file (the file has been generated with the `detektGenerateConfig` task).



pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Lesson 3"
include(":IntentApp")
include(":FavoriteBook")
include(":systemintentsapp")
include(":simplefragmentapp")
include(":sharer")

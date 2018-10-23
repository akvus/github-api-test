package com.waysnpaths.mygithub.data.networking

import android.content.res.AssetManager
import java.io.FileNotFoundException
import java.io.IOException

class StubbedNetworking(private val assetManager: AssetManager) : Networking() {

    @Throws(IOException::class)
    override fun get(url: String): String {
        return readAsset(when (url) {
            "https://api.github.com/users/akvus/repos" -> "akvus_repos_stub.json"
            "https://api.github.com/repos/akvus/android-arch-exercise/commits" -> "android_arch_excercise_commits.json"
            "https://api.github.com/repos/akvus/github-api-test/commits" -> "github_api_test_commits.json"
            "https://api.github.com/repos/akvus/lifeaims/commits" -> "lifeaims_commits.json"
            "https://api.github.com/repos/akvus/ng2-goo-maps/commits" -> "ng2_goo_maps_commits.json"
            "https://api.github.com/repos/akvus/skeleton-android/commits" -> "skeleton_android_commits.json"
            else -> throw FileNotFoundException(url)
        })
    }

    private fun readAsset(name: String): String {
        return assetManager.open(name).bufferedReader().use { it.readText() }
    }
}
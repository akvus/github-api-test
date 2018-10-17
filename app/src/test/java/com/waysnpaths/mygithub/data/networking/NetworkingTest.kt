package com.waysnpaths.mygithub.data.networking

import org.junit.Test

class NetworkingTest {

    // simple test to check if get() is working
    @Test
    fun get() {
        val result = Networking().get("https://api.github.com/users/akvus/repos")
        assert(result.isNotEmpty())
    }
}
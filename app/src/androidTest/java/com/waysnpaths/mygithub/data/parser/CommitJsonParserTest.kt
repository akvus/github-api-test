package com.waysnpaths.mygithub.data.parser

import com.waysnpaths.mygithub.data.networking.Networking
import org.junit.Test

class CommitJsonParserTest {

    @Test
    fun parse() {
        val result = Networking().get("https://api.github.com/repos/akvus/android_arch_exercise/commits")

        val commitsList = CommitJsonParser().parse(result)

        assert(commitsList.isNotEmpty())
    }
}
package com.waysnpaths.mygithub.data.parser

import com.waysnpaths.mygithub.data.networking.Networking
import org.junit.Test

class ReposJsonParserTest {

    @Test
    fun parse() {
        val result = Networking().get("https://api.github.com/users/akvus/repos")

        val reposList = ReposJsonParser().parse(result)

        assert(reposList.isNotEmpty())
    }
}
package com.waysnpaths.mygithub.data.networking

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class Networking {

    @Throws(IOException::class)
    fun get(url: String): String {
        val url = URL(url);
        val reader = BufferedReader(InputStreamReader(url.openStream()))
        val result = reader.readLines().joinToString()
        reader.close()
        return result
    }
}
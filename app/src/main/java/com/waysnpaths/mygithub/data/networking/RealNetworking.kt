package com.waysnpaths.mygithub.data.networking

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class RealNetworking : Networking() {
    @Throws(IOException::class)
    override fun get(url: String): String {
        val reader = BufferedReader(InputStreamReader(URL(url).openStream()))
        val result = reader.readLines().joinToString()
        reader.close()
        return result
    }
}
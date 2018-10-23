package com.waysnpaths.mygithub.data.networking

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class RealNetworking(private val baseUrl: String) : Networking() {
    @Throws(IOException::class)
    override fun get(url: String): String {
        val reader = BufferedReader(InputStreamReader(URL(baseUrl + url).openStream()))
        val result = reader.readLines().joinToString()
        reader.close()
        return result
    }
}
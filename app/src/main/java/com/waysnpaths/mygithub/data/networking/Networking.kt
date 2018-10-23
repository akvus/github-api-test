package com.waysnpaths.mygithub.data.networking

import java.io.IOException

abstract class Networking {
    @Throws(IOException::class)
    abstract fun get(url: String): String
}
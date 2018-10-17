package com.waysnpaths.mygithub.domain

interface JsonParser<T> {
    fun parse(input: String): T
}
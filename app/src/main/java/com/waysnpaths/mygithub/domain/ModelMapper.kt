package com.waysnpaths.mygithub.domain

interface ModelMapper<F, T> {
    fun map(from: F): T
    fun mapList(from: List<F>): MutableList<T>

    fun mapBack(from: T): F // todo maybe in another mapper...
    fun mapListBack(from: List<T>): MutableList<F>
}
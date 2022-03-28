package com.example.vokabeln.utils.collections

fun <T> List<List<T>>.unpack(): List<T> {
    val new = mutableListOf<T>()
    forEach {
        it.forEach {
            new += it
        }
    }
    return new
}
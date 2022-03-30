package com.example.vokabeln.utils.primitives

fun <T> MutableList<T>.without(item: T): MutableList<T> {
    val new = mutableListOf<T>()
    new.addAll(this)
    new.remove(item)
    return new
}
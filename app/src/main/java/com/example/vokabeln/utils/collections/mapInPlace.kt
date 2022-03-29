package com.example.vokabeln.utils.collections

fun <T> MutableList<T>.mapInPlace(mutator: (T) -> (T)) {
    this.forEachIndexed { i, value ->
        val changedValue = mutator(value)

        this[i] = changedValue
    }
}
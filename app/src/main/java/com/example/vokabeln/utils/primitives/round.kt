package com.example.vokabeln.utils.primitives

fun Double.round(digits: Int): Double {
    var multiplier = 1
    repeat(digits) {
        multiplier *= 10
    }
    return kotlin.math.round(this * multiplier) / multiplier
}
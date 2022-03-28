package com.example.vokabeln.utils.primitives

fun String.removeInBrackets(): String {
    var new = ""
    var delete = false

    forEach {
        if (it.toString() == "(") delete = true
        else if (it.toString() == ")") delete = false
        else if (!delete) new += it
    }
    return new
}

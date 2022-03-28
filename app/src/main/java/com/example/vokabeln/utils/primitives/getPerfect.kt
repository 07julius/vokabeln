package com.example.vokabeln.utils.primitives


fun String.getPerfect(): MutableList<String> {
    val withoutBrackets = replace("(", "").replace(")", "")
    val withoutContentInBrackets = removeInBrackets()
    val list = mutableListOf(withoutBrackets)
    if (withoutContentInBrackets != withoutBrackets) list += withoutContentInBrackets

    return list
}

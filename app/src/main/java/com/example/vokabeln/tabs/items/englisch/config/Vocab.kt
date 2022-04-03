package com.example.vokabeln.tabs.items.englisch.config

@kotlinx.serialization.Serializable
data class Vocab(val english: List<String>, val german: List<String>, var guessedRight: Int, var guessedWrong: Int) {
    override fun hashCode(): Int {
        var result = english.hashCode()
        result = 31 * result + german.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vocab

        if (english != other.english) return false
        if (german != other.german) return false
        if (guessedRight != other.guessedRight) return false
        if (guessedWrong != other.guessedWrong) return false

        return true
    }
}

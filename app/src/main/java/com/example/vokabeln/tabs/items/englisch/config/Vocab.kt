package com.example.vokabeln.tabs.items.englisch.config

@kotlinx.serialization.Serializable
data class Vocab(val english: List<String>, val german: List<String>, var guessedRight: Int, var guessedWrong: Int) {
    override fun equals(other: Any?): Boolean {
        if (other !is Vocab) return false
        return hashCode() == other.hashCode()
    }

    override fun hashCode(): Int {
        var result = english.hashCode()
        result = 31 * result + german.hashCode()
        return result
    }
}

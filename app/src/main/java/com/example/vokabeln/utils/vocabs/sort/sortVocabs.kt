package com.example.vokabeln.utils.vocabs.sort

import com.example.vokabeln.tabs.items.englisch.config.Vocab

fun List<Vocab>.sortByEnglish(): List<Vocab> {
    return sortedBy { vocab ->
        vocab.english.joinToString { string -> string }
    }
}

fun List<Vocab>.sortByGerman(): List<Vocab> {
    return sortedBy { vocab ->
        vocab.german.joinToString { string -> string }
    }
}

fun List<Vocab>.sortByRight(): List<Vocab> {
    return sortedBy { vocab ->
        vocab.guessedRight
    }.reversed()
}

fun List<Vocab>.sortByWrong(): List<Vocab> {
    return sortedBy { vocab ->
        vocab.guessedWrong
    }.reversed()
}

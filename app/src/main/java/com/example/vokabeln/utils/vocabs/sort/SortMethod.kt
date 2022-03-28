package com.example.vokabeln.utils.vocabs.sort

import com.example.vokabeln.tabs.items.englisch.config.Vocab

enum class SortMethod {
    ByEnglish {
        override fun sort(list: List<Vocab>) = list.sortByEnglish()
    },
    ByGerman {
        override fun sort(list: List<Vocab>) = list.sortByGerman()
    },
    ByRight {
        override fun sort(list: List<Vocab>) = list.sortByRight()
    },
    ByWrong {
        override fun sort(list: List<Vocab>) = list.sortByWrong()
    };

    abstract fun sort(list: List<Vocab>): List<Vocab>
}
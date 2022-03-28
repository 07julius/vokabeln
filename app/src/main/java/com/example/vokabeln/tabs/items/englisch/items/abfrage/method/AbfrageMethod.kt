package com.example.vokabeln.tabs.items.englisch.items.abfrage.method

enum class AbfrageMethod {
    ENGLISHGIVEN {
        override fun next() = CheckBoxesEnglishGiven
        override fun nextInLanguage() = CheckBoxesEnglishGiven
    },

    GERMANGIVEN {
        override fun next() = CheckBoxesGermanGiven
        override fun nextInLanguage() = CheckBoxesGermanGiven
    },

    CheckBoxesEnglishGiven {
        override fun next() = GERMANGIVEN
        override fun nextInLanguage() = ENGLISHGIVEN
    },

    CheckBoxesGermanGiven {
        override fun next() = ENGLISHGIVEN
        override fun nextInLanguage() = GERMANGIVEN
    };

    abstract fun next(): AbfrageMethod
    abstract fun nextInLanguage(): AbfrageMethod
}
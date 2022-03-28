package com.example.vokabeln

enum class Languages(val germanName: String) {
    ENGLISH("englisch") {
        override fun next() = LITHUANIAN
    },
    LITHUANIAN("litauisch") {
        override fun next() = ENGLISH
    };

    abstract fun next(): Languages
}
package com.example.vokabeln.tabs.items.englisch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.ui.graphics.Color
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.tab.Tab
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.tabs.items.englisch.items.addVocab
import com.example.vokabeln.tabs.items.englisch.items.vokabeln

val englisch = TabItem(Icons.Filled.BookmarkAdd, MainActivity.language) {
    Tab(
        listOf(addVocab, vokabeln, Abfrage.abfrage),
        Color(216, 27, 96)
    )
}
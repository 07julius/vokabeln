package com.example.vokabeln.tabs.items.englisch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.tab.Tab
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.tabs.items.englisch.items.addVocab
import com.example.vokabeln.tabs.items.englisch.items.allVocabs.vokabeln
import com.google.accompanist.pager.ExperimentalPagerApi

val englisch = TabItem(Icons.Filled.BookmarkAdd, MainActivity.language) {
    @OptIn(ExperimentalPagerApi::class)
    Tab(
        mutableStateListOf(addVocab, vokabeln, Abfrage.abfrage),
        Color(216, 27, 96)
    )
}
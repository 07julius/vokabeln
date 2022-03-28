package com.example.vokabeln.tabs.items.englisch.items.abfrage.english.text

import androidx.compose.runtime.Composable
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.tabs.items.englisch.items.abfrage.abstract.lanGiven
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Abfrage.englishGiven() {
    lanGiven(given = item?.english, searchIn = item?.german, inputLabel = "deutsche wörter")
}
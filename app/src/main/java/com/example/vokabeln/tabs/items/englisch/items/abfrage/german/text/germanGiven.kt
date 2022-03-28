package com.example.vokabeln.tabs.items.englisch.items.abfrage.german.text

import androidx.compose.runtime.Composable
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.tabs.items.englisch.items.abfrage.abstract.lanGiven
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Abfrage.germanGiven() {
    lanGiven(given = item?.german, searchIn = item?.english, inputLabel = "${MainActivity.language}e wörter")
}
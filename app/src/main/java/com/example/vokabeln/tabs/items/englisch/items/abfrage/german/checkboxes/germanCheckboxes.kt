package com.example.vokabeln.tabs.items.englisch.items.abfrage.german.checkboxes


import androidx.compose.runtime.Composable
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.tabs.items.englisch.items.abfrage.abstract.checkboxes
import com.example.vokabeln.utils.primitives.getPerfect
import com.example.vokabeln.utils.primitives.without
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Abfrage.germanCheckboxes() {
    val list = AndroidConfig.instance.vocabs.without(item!!).shuffled().subList(0, defaultNumCheckboxes).map { it.german.random() }.toMutableList()

    list[(0 until list.size).random()] =
        item?.german?.random()?.getPerfect()?.random() ?: AndroidConfig.instance.vocabs.random().german.random()
            .getPerfect().random()

    checkboxes(
        item?.english,
        item?.german,
        list
    )
}
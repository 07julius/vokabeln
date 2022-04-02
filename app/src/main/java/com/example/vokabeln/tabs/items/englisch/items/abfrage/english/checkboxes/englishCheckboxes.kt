package com.example.vokabeln.tabs.items.englisch.items.abfrage.english.checkboxes

import androidx.compose.runtime.Composable
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.tabs.items.englisch.items.abfrage.abstract.checkboxes
import com.example.vokabeln.utils.collections.repeating
import com.example.vokabeln.utils.collections.unpack
import com.example.vokabeln.utils.primitives.getPerfect
import com.example.vokabeln.utils.primitives.without
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlin.random.Random

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Abfrage.englishCheckboxes() {
    val list = (AndroidConfig.instance.getVocabsAtKey(AndroidConfig.instance.key)?.without(item!!) ?: AndroidConfig.instance.vocabs.map { it.value }.unpack()).shuffled()
        .subList(0, defaultNumCheckboxes)
        .map { it.english.random() }.toMutableList()

    list[(0 until list.size).random()] =
        (item?.english?.random()?.getPerfect()?.random() ?: AndroidConfig.instance.getVocabsAtKey(AndroidConfig.instance.key)?.random()?.english?.random()
            ?.getPerfect()?.random()).toString()

    checkboxes(
        item?.german,
        item?.english,
        list
    )
}
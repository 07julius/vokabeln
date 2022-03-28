package com.example.vokabeln.tabs.items.englisch.items.abfrage.english.checkboxes

import androidx.compose.runtime.Composable
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.tabs.items.englisch.items.abfrage.abstract.checkboxes
import com.example.vokabeln.utils.collections.repeating
import com.example.vokabeln.utils.primitives.getPerfect
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlin.random.Random

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Abfrage.englishCheckboxes() {
    val list = repeating(defaultNumCheckboxes) {
        AndroidConfig.instance.vocabs.shuffled(Random(Random.nextInt() + it)).minus(item!!).random().english.random()
            .getPerfect().random()
    }

    list[(0 until list.size).random()] =
        item?.english?.random()?.getPerfect()?.random() ?: AndroidConfig.instance.vocabs.random().english.random()
            .getPerfect().random()

    checkboxes(
        item?.german,
        item?.english,
        list
    )
}
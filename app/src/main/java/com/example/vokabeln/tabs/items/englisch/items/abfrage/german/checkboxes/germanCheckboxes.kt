package com.example.vokabeln.tabs.items.englisch.items.abfrage.german.checkboxes

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
fun Abfrage.germanCheckboxes() {
    val list = repeating(defaultNumCheckboxes) {
        AndroidConfig.instance.vocabs.shuffled(Random(Random.nextInt() + it)).minus(item!!).random().german.random()
            .getPerfect().random()
    }

    list[(0 until list.size).random()] =
        item?.german?.random()?.getPerfect()?.random() ?: AndroidConfig.instance.vocabs.random().german.random()
            .getPerfect().random()

    checkboxes(
        item?.english,
        item?.german,
        list
    )
}
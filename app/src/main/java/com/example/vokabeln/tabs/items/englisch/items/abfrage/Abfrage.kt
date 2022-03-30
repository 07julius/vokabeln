package com.example.vokabeln.tabs.items.englisch.items.abfrage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.items.abfrage.english.checkboxes.englishCheckboxes
import com.example.vokabeln.tabs.items.englisch.items.abfrage.english.text.englishGiven
import com.example.vokabeln.tabs.items.englisch.items.abfrage.german.checkboxes.germanCheckboxes
import com.example.vokabeln.tabs.items.englisch.items.abfrage.german.text.germanGiven
import com.example.vokabeln.tabs.items.englisch.items.abfrage.method.AbfrageMethod
import com.example.vokabeln.utils.vocabs.getWorst

object Abfrage {
    var item by mutableStateOf(AndroidConfig.instance.vocabs.getWorst())
    var method by mutableStateOf(AbfrageMethod.values().random())
    const val defaultNumCheckboxes = 4

    val abfrage: TabItem
        @Composable get() = TabItem(Icons.Filled.QuestionAnswer, "abfrage") {
            when (method) {
                AbfrageMethod.ENGLISHGIVEN -> englishGiven()
                AbfrageMethod.GERMANGIVEN -> germanGiven()
                AbfrageMethod.CheckBoxesGermanGiven -> {
                    if (AndroidConfig.instance.vocabs.size >= defaultNumCheckboxes) englishCheckboxes() else germanGiven()
                }
                AbfrageMethod.CheckBoxesEnglishGiven -> {
                    if (AndroidConfig.instance.vocabs.size >= defaultNumCheckboxes) germanCheckboxes() else englishGiven()
                }
            }

        }
}
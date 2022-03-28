package com.example.vokabeln.tabs.items.englisch.items.abfrage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.config.Vocab
import com.example.vokabeln.tabs.items.englisch.items.abfrage.english.checkboxes.englishCheckboxes
import com.example.vokabeln.tabs.items.englisch.items.abfrage.english.text.englishGiven
import com.example.vokabeln.tabs.items.englisch.items.abfrage.german.checkboxes.germanCheckboxes
import com.example.vokabeln.tabs.items.englisch.items.abfrage.german.text.germanGiven
import com.example.vokabeln.tabs.items.englisch.items.abfrage.method.AbfrageMethod
import com.example.vokabeln.theme.Modifiers
import com.example.vokabeln.utils.compose.TabItemText
import com.example.vokabeln.utils.vocabs.getWorst
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Abfrage {
    var item by mutableStateOf(AndroidConfig.instance.vocabs.getWorst())
    var method by mutableStateOf(AbfrageMethod.values().random())
    const val defaultNumCheckboxes = 4
    private val scope: CoroutineScope
        @Composable get() = rememberCoroutineScope()

    val abfrage: TabItem
        @Composable get() = TabItem(Icons.Filled.QuestionAnswer, "abfrage") {
            start()

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

    @Composable
    fun start() = scope.launch {
        while (true) {
            delay(100)
            item = AndroidConfig.instance.vocabs.getWorst()
        }
    }
}
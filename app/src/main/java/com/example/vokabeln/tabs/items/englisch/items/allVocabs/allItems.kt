package com.example.vokabeln.tabs.items.englisch.items.allVocabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.config.Vocab
import com.example.vokabeln.theme.Modifiers
import com.example.vokabeln.theme.TextSizes
import com.example.vokabeln.utils.collections.unpack
import com.example.vokabeln.utils.compose.TableCell
import com.example.vokabeln.utils.vocabs.sort.SortMethod
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class) @Composable
fun AllItems(selectable: Boolean = false, onSelectCallback: (items: List<Vocab>) -> Unit = { }) {
    var sortMethod by remember { mutableStateOf(SortMethod.ByEnglish) }
    val column1Weight = .6f
    val column2Weight = .4f

    val selected = mutableStateMapOf<Vocab, Boolean>()

    LazyColumn(Modifiers.tabItemLazyColumnModifier) {
        item {
            Row(Modifier.background(Color(128, 128, 128, 95))) {
                TableCell(
                    text = MainActivity.language,
                    weight = column1Weight,
                    FontWeight(200),
                    fontSize = TextSizes.topTableTextSize,
                    modifier = Modifier.clickable {
                        sortMethod = SortMethod.ByEnglish
                    })
                TableCell(
                    text = "deutsch",
                    weight = column1Weight,
                    FontWeight(200),
                    fontSize = TextSizes.topTableTextSize,
                    modifier = Modifier.clickable {
                        sortMethod = SortMethod.ByGerman
                    })
                TableCell(
                    text = "richtig",
                    weight = column2Weight,
                    FontWeight(200),
                    fontSize = TextSizes.topTableTextSize,
                    modifier = Modifier.clickable {
                        sortMethod = SortMethod.ByRight
                    })
                TableCell(
                    text = "falsch",
                    weight = column2Weight,
                    FontWeight(200),
                    fontSize = TextSizes.topTableTextSize,
                    modifier = Modifier.clickable {
                        sortMethod = SortMethod.ByWrong
                    })
            }
        }

        itemsIndexed(
            sortMethod.sort(
                AndroidConfig.instance.vocabs.map { it.value }.distinct().toMutableList().unpack().distinct()
            )
        ) { _, item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (selectable) {
                            selected[item] = selected[item] != true
                            onSelectCallback(
                                selected
                                    .withValue(true)
                                    .map { it.first })
                        }
                    }
            ) {
                TableCell(
                    text = item.english.joinToString(",\n") { it },
                    weight = column1Weight,
                    fontSize = TextSizes.bottomTableTextSize,
                    textDecoration = if (selected[item] == true) TextDecoration.Underline else null
                )
                TableCell(
                    text = item.german.joinToString(",\n") { it },
                    weight = column1Weight,
                    fontSize = TextSizes.bottomTableTextSize,
                    textDecoration = if (selected[item] == true) TextDecoration.Underline else null
                )
                TableCell(
                    text = "${item.guessedRight}",
                    weight = column2Weight,
                    fontSize = TextSizes.bottomTableTextSize,
                    textDecoration = if (selected[item] == true) TextDecoration.Underline else null
                )
                TableCell(
                    text = "${item.guessedWrong}",
                    weight = column2Weight,
                    fontSize = TextSizes.bottomTableTextSize,
                    textDecoration = if (selected[item] == true) TextDecoration.Underline else null
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class) @Composable
fun allItems(selectable: Boolean = false, onSelectCallback: (items: List<Vocab>) -> Unit = { }): TabItem = TabItem(null, "alle") {
    AllItems(selectable, onSelectCallback)
}

fun <K, V> Map<K, V>.withKey(key: K): MutableList<Pair<K, V>> {
    val haveKey = mutableListOf<Pair<K, V>>()
    forEach { (k, v) ->
        if (k == key) haveKey += k to v
    }
    return haveKey
}

fun <K, V> Map<K, V>.withValue(value: V): MutableList<Pair<K, V>> {
    val haveValue = mutableListOf<Pair<K, V>>()
    forEach { (k, v) ->
        if (v == value) haveValue += k to v
    }
    return haveValue
}
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
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.theme.Modifiers
import com.example.vokabeln.theme.TextSizes
import com.example.vokabeln.utils.collections.unpack
import com.example.vokabeln.utils.compose.TableCell
import com.example.vokabeln.utils.vocabs.sort.SortMethod
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class) @Composable
fun allItems(): TabItem = TabItem(null, "alle") {
    var sortMethod by remember { mutableStateOf(SortMethod.ByEnglish) }
    val column1Weight = .6f
    val column2Weight = .4f

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
                AndroidConfig.instance.vocabs.map { it.value }.toMutableList().unpack()
            )
        ) { index, item ->
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                TableCell(
                    text = item.english.joinToString(",\n") { it },
                    weight = column1Weight,
                    fontSize = TextSizes.bottomTableTextSize
                )
                TableCell(
                    text = item.german.joinToString(",\n") { it },
                    weight = column1Weight,
                    fontSize = TextSizes.bottomTableTextSize
                )
                TableCell(
                    text = "${item.guessedRight}",
                    weight = column2Weight,
                    fontSize = TextSizes.bottomTableTextSize
                )
                TableCell(
                    text = "${item.guessedWrong}",
                    weight = column2Weight,
                    fontSize = TextSizes.bottomTableTextSize
                )
            }
        }
    }
}
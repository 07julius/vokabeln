package com.example.vokabeln.tabs.items.englisch.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.config.Vocab
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.theme.Colors
import com.example.vokabeln.theme.Modifiers
import com.example.vokabeln.theme.TextSizes
import com.example.vokabeln.utils.collections.mapInPlace
import com.example.vokabeln.utils.compose.TableCell
import com.example.vokabeln.utils.compose.icons.Trash
import com.example.vokabeln.utils.vocabs.getWorst
import com.example.vokabeln.utils.vocabs.sort.SortMethod
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val vokabeln: TabItem
    @OptIn(ExperimentalPagerApi::class) @Composable get() = TabItem(Icons.Filled.List, "vokabeln") {
        val scope = rememberCoroutineScope()
        var isDeleting = false
        val list = AndroidConfig.instance.vocabs.toMutableStateList()
        val itemsDeleting = hashMapOf<Vocab, Boolean>()
        var sortMethod by remember { mutableStateOf(SortMethod.ByEnglish) }

        fun startDelete(delay: Long = 250) = scope.launch {
            isDeleting = true
            delay(delay)
            isDeleting = false
        }

        fun itemDeleted(item: Vocab, index: Int, delay: Long = 250) = scope.launch {
            if (itemsDeleting[item] == true) {
                AndroidConfig.instance.vocabs.remove(item)
                AndroidConfig.instance.saveVocabs()
                Abfrage.item = AndroidConfig.instance.vocabs.getWorst()
                MainActivity.makeTaost("vokabel gelöscht")
                return@launch
            }

            itemsDeleting[item] = true
            delay(delay)
            itemsDeleting[item] = false
        }

        val column1Weight = .6f
        val column2Weight = .4f

        LazyColumn(Modifiers.tabItemLazyColumnModifier) {
            if (list.isNotEmpty()) {
                item {
                    Row(Modifier.background(Color(128, 128, 128, 95))) {
                        TableCell(text =  MainActivity.language, weight = column1Weight, FontWeight(200), fontSize = TextSizes.topTableTextSize, modifier = Modifier.clickable {
                            sortMethod = SortMethod.ByEnglish
                        })
                        TableCell(text = "deutsch", weight = column1Weight, FontWeight(200), fontSize = TextSizes.topTableTextSize, modifier = Modifier.clickable {
                            sortMethod = SortMethod.ByGerman
                        })
                        TableCell(text = "richtig", weight = column2Weight, FontWeight(200), fontSize = TextSizes.topTableTextSize, modifier = Modifier.clickable {
                            sortMethod = SortMethod.ByRight
                        })
                        TableCell(text = "falsch", weight = column2Weight, FontWeight(200), fontSize = TextSizes.topTableTextSize, modifier = Modifier.clickable {
                            sortMethod = SortMethod.ByWrong
                        })
                    }
                }

                itemsIndexed(sortMethod.sort(list)) { index, item ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                itemDeleted(item, index)
                            }) {
                        TableCell(
                            text = item.english.joinToString(",\n") { it },
                            weight = column1Weight,
                            fontSize = TextSizes.bottomTableTextSize
                        )
                        TableCell(text = item.german.joinToString(",\n") { it }, weight = column1Weight, fontSize = TextSizes.bottomTableTextSize)
                        TableCell(text = "${item.guessedRight}", weight = column2Weight, fontSize = TextSizes.bottomTableTextSize)
                        TableCell(text = "${item.guessedWrong}", weight = column2Weight, fontSize = TextSizes.bottomTableTextSize)
                    }
                }

                item {
                    Column(Modifiers.tabItemLazyColumnModifier) {
                        Spacer(Modifier.padding(vertical = 10.dp))

                        Button(
                            onClick = {
                                if (isDeleting) {
                                    AndroidConfig.instance.deleted()
                                    isDeleting = false
                                }
                                startDelete()
                            },
                            content = {
                                Text("alle (${AndroidConfig.instance.vocabs.size}) löschen     ")
                                Icon(imageVector = Icons.Filled.Trash, contentDescription = "")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(255, 10, 10, 95))
                        )

                        Button(
                            onClick = {
                                AndroidConfig.instance.vocabs.forEach {
                                    it.apply {
                                        it.guessedRight = 0
                                        it.guessedWrong = 0
                                    }
                                }
                                AndroidConfig.instance.vocabs += Vocab(listOf(), listOf(), -1, -1)
                                AndroidConfig.instance.vocabs -= Vocab(listOf(), listOf(), -1, -1)
                                Abfrage.item = AndroidConfig.instance.vocabs.getWorst()
                                AndroidConfig.instance.saveVocabs()
                            },
                            content = {
                                Text("alle zurücksetzen     ")
                                Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(230, 20, 20, 93))
                        )

                    }
                }
            } else {
                item {
                    Column(Modifiers.tabItemLazyColumnModifier) {
                        Button(
                            onClick = {
                                scope.launch {
                                    MainActivity.pagerState.animateScrollToPage(0)
                                }
                            },
                            content = {
                                Text("Vokabel hinzufügen     ")
                                Icon(imageVector = Icons.Filled.PlusOne, contentDescription = "")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = Colors.tabItemButtonColors,
                        )
                    }
                }
            }
        }
    }
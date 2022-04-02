package com.example.vokabeln.tabs.items.englisch.items.allVocabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.theme.Colors
import com.example.vokabeln.theme.Modifiers
import com.example.vokabeln.theme.TextSizes
import com.example.vokabeln.utils.compose.TableCell
import com.example.vokabeln.utils.compose.icons.Trash
import com.example.vokabeln.utils.compose.onDoubleClick
import com.example.vokabeln.utils.vocabs.getWorst
import com.example.vokabeln.utils.vocabs.sort.SortMethod
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class) @Composable
fun itemBy(it: Int): TabItem = TabItem(null, AndroidConfig.instance.vocabs.keys.toList()[it]) {
    val scope = rememberCoroutineScope()
    var sortMethod by remember { mutableStateOf(SortMethod.ByEnglish) }
    val column1Weight = .6f
    val column2Weight = .4f

    LazyColumn(Modifiers.tabItemLazyColumnModifier) {
        if (AndroidConfig.instance.vocabs[AndroidConfig.instance.keyBy(it)]?.isNotEmpty() == true) {
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
                    AndroidConfig.instance.vocabs[AndroidConfig.instance.keyBy(it)] ?: mutableListOf()
                )
            ) { _, item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = {},
                            onDoubleClick = {
                                AndroidConfig.instance.vocabs[AndroidConfig.instance.keyBy(it)]?.remove(
                                    item
                                )
                                AndroidConfig.instance.saveVocabs()
                                AndroidConfig.instance.update()
                                Abfrage.item =
                                    AndroidConfig.instance.vocabs[AndroidConfig.instance.keyBy(it)]?.getWorst()
                                MainActivity.makeTaost("vokabel gelöscht")
                            }
                        )) {
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

            item {
                Column(Modifiers.tabItemLazyColumnModifier) {
                    Spacer(Modifier.padding(vertical = 10.dp))
                    Column {
                        Button(
                            onClick = {
                                if (deleting) {
                                    AndroidConfig.instance.vocabs.remove(AndroidConfig.instance.keyBy(it))
                                    deleting = false
                                } else {
                                    scope.launch {
                                        deleting = true
                                        delay(500)
                                        deleting = false
                                    }
                                }

                                AndroidConfig.instance.saveVocabs()
                            },
                            content = {
                                Text(
                                    "tab (${
                                        AndroidConfig.instance.vocabs[AndroidConfig.instance.keyBy(
                                            it
                                        )]?.size
                                    }) löschen     "
                                )
                                Icon(imageVector = Icons.Filled.Trash, contentDescription = "")
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(
                                    255,
                                    10,
                                    10,
                                    95
                                )
                            )
                        )
                    }

                    Button(
                        onClick = {
                            AndroidConfig.instance.getVocabsAtKey(AndroidConfig.instance.key)?.forEach {
                                it.apply {
                                    guessedRight = 0
                                    guessedWrong = 0
                                }
                            }
                            AndroidConfig.instance.update()
                            Abfrage.item =
                                AndroidConfig.instance.vocabs[AndroidConfig.instance.keyBy(it)]?.getWorst()
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
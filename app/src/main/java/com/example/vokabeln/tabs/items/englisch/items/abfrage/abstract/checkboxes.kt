package com.example.vokabeln.tabs.items.englisch.items.abfrage.abstract

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.config.Vocab
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.theme.Colors
import com.example.vokabeln.theme.Modifiers
import com.example.vokabeln.utils.collections.unpack
import com.example.vokabeln.utils.compose.TabItemText
import com.example.vokabeln.utils.primitives.getPerfect
import com.example.vokabeln.utils.vocabs.getWorst
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Abfrage.checkboxes(given: List<String>?, searchIn: List<String>?, texts: List<String>) {
    val boxes = defaultNumCheckboxes
    val checked = mutableStateMapOf<Int, Boolean>()
    val scope = rememberCoroutineScope()

    fun getChecked(): Pair<Int, Boolean>? {
        checked.forEach { (i, b) ->
            if (b) return i to b
        }
        return null
    }

    repeat(boxes) {
        checked[it] = false
    }

    LazyColumn(modifier = Modifiers.tabItemColumnModifier) {
        item {

            TabItemText(text = given?.joinToString { it } ?: "vokabeln erstellen", modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (item == null) {
                        scope.launch {
                            MainActivity.pagerState.animateScrollToPage(0)
                        }
                        return@clickable
                    }
                    method = method.nextInLanguage()
                })
        }

        if (given != null) {
            items(boxes) {
                Row {
                    Checkbox(
                        checked = checked[it]!!,
                        onCheckedChange = { _ ->
                            checked.forEach { (i, _) ->
                                checked[i] = false
                            }
                            checked[it] = true
                        }
                    )

                    TabItemText(texts[it].lowercase(Locale.getDefault()).trim(), modifier = Modifier
                        .clickable {
                            checked.forEach { (i, _) ->
                                checked[i] = false
                            }
                            checked[it] = true
                        }
                        .padding(vertical = 8.dp))
                }
            }

            item {
                Button(onClick = {
                    if (checked.values.all { !it }) return@Button

                    if (searchIn?.map { it.lowercase(Locale.getDefault()) }
                            ?.map { it.getPerfect().map { it.trim() } }?.unpack()?.contains(
                                texts[getChecked()?.first ?: 0].lowercase(Locale.getDefault()).trim()
                            ) == true
                    ) {
                        //item?.guessedRight = (item?.guessedRight ?: 0) + 1
                        AndroidConfig.instance.vocabs[AndroidConfig.instance.vocabs.indexOf(item!!)] =
                            Vocab(item!!.english, item!!.german, (item?.guessedRight ?: 0) + 1, item!!.guessedWrong)
                        method = method.next()
                        //item = AndroidConfig.instance.vocabs.getWorst()
                        MainActivity.makeTaost("richtig")
                    } else {
                        //item?.guessedWrong = (item?.guessedWrong ?: 0) + 1
                        AndroidConfig.instance.vocabs[AndroidConfig.instance.vocabs.indexOf(item!!)] =
                            Vocab(item!!.english, item!!.german, item!!.guessedRight, (item?.guessedWrong ?: 0) + 1)

                        method = method.nextInLanguage()
                        MainActivity.makeTaost("falsch")
                    }

                    AndroidConfig.instance.saveVocabs()

                    checked.forEach { (i, _) ->
                        checked[i] = false
                    }
                    item = AndroidConfig.instance.vocabs.getWorst()
                }, colors = Colors.tabItemButtonColors, modifier = Modifier.fillMaxWidth()) {
                    Text("next")
                }
            }
        }
    }
}
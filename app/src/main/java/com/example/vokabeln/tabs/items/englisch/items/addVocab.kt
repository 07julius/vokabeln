package com.example.vokabeln.tabs.items.englisch.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.config.Vocab
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.theme.Colors
import com.example.vokabeln.theme.Modifiers
import com.example.vokabeln.utils.vocabs.getWorst
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
val addVocab: TabItem
    @Composable get() = TabItem(Icons.Filled.PlusOne, "vokabel hinzufügen") {
        var englishState by remember { mutableStateOf("") }
        var deutschState by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()

        Column(modifier = Modifiers.tabItemColumnModifier) {
            TextField(
                value = englishState,
                onValueChange = {
                    englishState = it
                },
                label = {
                    Text("${MainActivity.language}e wörter")
                },
                colors = Colors.tabItemTextFieldColors,
                modifier = Modifiers.tabItemTextFieldModifier
            )

            TextField(
                value = deutschState,
                onValueChange = {
                    deutschState = it
                },
                label = {
                    Text("deutsche wörter")
                },
                colors = Colors.tabItemTextFieldColors,
                modifier = Modifiers.tabItemTextFieldModifier
            )

            Spacer(modifier = Modifier.padding(10.dp))


            Button(
                onClick = {
                    if (englishState.isNotEmpty() && deutschState.isNotEmpty()) {
                        val vocab = Vocab(englishState.trim().split(","),
                            deutschState.trim().split(","),
                            0,
                            0
                        )
                        if (vocab !in AndroidConfig.instance.vocabs) {
                            AndroidConfig.instance.vocabs += vocab
                            Abfrage.item = AndroidConfig.instance.vocabs.getWorst()
                            MainActivity.makeTaost("vokabel erstellt")
                        } else {
                            MainActivity.makeTaost("vokabel existiert bereits")
                        }
                        englishState = ""
                        deutschState = ""
                        AndroidConfig.instance.saveVocabs()

                        scope.launch {
                            MainActivity.pagerState.animateScrollToPage(1)
                        }
                    } else {
                        MainActivity.makeTaost("${MainActivity.language} und deutsch müssen gegeben sein")
                    }
                }, colors = Colors.tabItemButtonColors,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Vokabel hinzufügen")
            }
        }
    }
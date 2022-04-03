package com.example.vokabeln.tabs.items.englisch.items.allVocabs

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
import com.example.vokabeln.theme.Colors
import com.example.vokabeln.theme.Modifiers
import com.example.vokabeln.utils.compose.TabItemText
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
val newTab = TabItem(Icons.Filled.PlusOne, "neuer tab") {
    var state by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var vocabsInTab = remember { listOf<Vocab>() }

    Column(modifier = Modifiers.tabItemColumnModifier) {
        TextField(
            value = state,
            onValueChange = {
                state = it
            },
            label = {
                Text("name")
            },
            colors = Colors.tabItemTextFieldColors,
            modifier = Modifiers.tabItemTextFieldModifier
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            onClick = {
                if (state.isNotEmpty()) {
                    if (!AndroidConfig.instance.vocabs.keys.contains(state) || state == "alle") {
                        AndroidConfig.instance.vocabs[state] = vocabsInTab.toMutableStateList()
                        MainActivity.makeTaost("tab erstellt")
                    } else {
                        MainActivity.makeTaost("tab existiert bereits")
                    }
                    AndroidConfig.instance.saveVocabs()
                    scope.launch {
                        MainActivity.pagerState.animateScrollToPage(AndroidConfig.instance.vocabs.indexOf(state).coerceAtLeast(0))
                    }
                    state = ""
                } else {
                    MainActivity.makeTaost("name darf nicht leer sein")
                }
            }, colors = Colors.tabItemButtonColors,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tab hinzufügen")
        }

        Spacer(modifier = Modifier.padding(10.dp))

        TabItemText("vokabeln")

        Spacer(modifier = Modifier.padding(10.dp))

        AllItems(true) {
            println(it)
            vocabsInTab = it
        }
    }
}

fun <K> Map<K, *>.indexOf(key: K): Int = map { it.key }.indexOf(key)
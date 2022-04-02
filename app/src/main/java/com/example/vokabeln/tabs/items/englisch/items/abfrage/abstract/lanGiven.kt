package com.example.vokabeln.tabs.items.englisch.items.abfrage.abstract

import android.view.KeyEvent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
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
fun Abfrage.lanGiven(given: List<String>?, searchIn: List<String>?, inputLabel: String) {
    var typedState by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    fun submit() {
        if (typedState.isEmpty()) return

        if (searchIn?.map { it.lowercase(Locale.getDefault()).trim() }?.map { it.getPerfect().map { it.trim() } }?.unpack()?.contains(
                typedState.trim().replace("\n", "")
                    .lowercase(Locale.getDefault())
            ) == true
        ) {
            AndroidConfig.instance.applyVocab(item!!) {
                guessedRight++
            }
            AndroidConfig.instance.update()

            method = method.next()
            item = AndroidConfig.instance.getVocabsAtKey(AndroidConfig.instance.key)?.getWorst()
            MainActivity.makeTaost("richtig")
        } else {
            if (AndroidConfig.instance.vocabs.isEmpty()) return
            AndroidConfig.instance.applyVocab(item!!) {
                guessedWrong++
            }
            AndroidConfig.instance.update()

            method = method.nextInLanguage()
            item = AndroidConfig.instance.getVocabsAtKey(AndroidConfig.instance.key)?.getWorst()
            MainActivity.makeTaost("falsch")
        }
        AndroidConfig.instance.saveVocabs()

        typedState = ""
        item = AndroidConfig.instance.getVocabsAtKey(AndroidConfig.instance.key)?.getWorst()
    }

    Column(modifier = Modifiers.tabItemColumnModifier) {

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
        TextField(
            value = typedState,
            onValueChange = {
                typedState = it.replace("\n", "")
            },
            label = {
                Text(inputLabel)
            },
            colors = Colors.tabItemTextFieldColors,
            modifier = Modifiers.tabItemTextFieldModifier.onKeyEvent {
                if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER){
                    submit()
                }
                false
            }
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = {
            submit()
        }, colors = Colors.tabItemButtonColors, modifier = Modifier.fillMaxWidth()) {
            Text("next")
        }
    }
}
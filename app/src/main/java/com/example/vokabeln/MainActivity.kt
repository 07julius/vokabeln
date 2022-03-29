package com.example.vokabeln

import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.tabs.items.englisch.items.addVocab
import com.example.vokabeln.tabs.items.englisch.items.vokabeln
import com.example.vokabeln.tabs.tab.Tab
import com.example.vokabeln.theme.Colors
import com.example.vokabeln.theme.TextSizes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


class MainActivity : ComponentActivity() {
    companion object {
        private var languageEnum by mutableStateOf(Languages.ENGLISH)
        val language: String get() = languageEnum.germanName

        var state by mutableStateOf("")
        val isTablet by lazy { /* ((instance.getHeight() > 2000) and (instance.getWidth() > 1200)) */ false }
        lateinit var instance: MainActivity
            private set

        @OptIn(ExperimentalPagerApi::class)
        lateinit var pagerState: PagerState
            private set

        fun makeTaost(text: String, duration: Int = Toast.LENGTH_LONG) {
            Toast.makeText(instance.applicationContext, text, duration).show()
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        setContent {
            pagerState = rememberPagerState()
            English()
        }
    }

    @Preview
    @Composable
    fun English() {
        state = language.uppercase()
        val interactionSource = remember { MutableInteractionSource() }
        Tab(
            listOf(addVocab, vokabeln, Abfrage.abfrage),
            Color(216, 27, 96),
            isTablet
        ) {
            TopAppBar(
                title = { Text(language.uppercase(), fontSize = TextSizes.topAppBarTextSize, modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    languageEnum = languageEnum.next()
                }) },
                backgroundColor = Colors.topAppBarBackgroundColor,
                contentColor = Colors.topAppBarContentColor
            )
        }
    }

    fun getHeight(): Int {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.heightPixels
    }

    fun getWidth(): Int {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.widthPixels
    }
}
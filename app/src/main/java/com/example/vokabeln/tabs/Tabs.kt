package com.example.vokabeln.tabs

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.utils.vocabs.getWorst
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: SnapshotStateList<TabItem>, pagerState: PagerState, backgroundColor: Color, isTop: Boolean = false) {
    val scope = rememberCoroutineScope()
    Abfrage.item = AndroidConfig.instance.getVocabsAtKey(AndroidConfig.instance.key)?.getWorst()

    if (!isTop) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = backgroundColor,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
            tabs.toList().forEachIndexed { index, tab ->
                LeadingIconTab(
                    icon = {
                        if (tab.icon != null) {
                            Icon(tab.icon!!, contentDescription = "")
                        }
                    },
                    text = { Text(tab.title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
    }
    else {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = backgroundColor,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
            tabs.forEachIndexed { index, tab ->
                LeadingIconTab(
                    icon = {
                        if (tab.icon != null) {
                            Icon(tab.icon!!, contentDescription = "")
                        }
                    },
                    text = { Text(tab.title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }

                        MainActivity.state = tab.title.uppercase(Locale.getDefault())
                    },
                )
            }
        }
    }
}
package com.example.vokabeln.tabs

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.item.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState, backgroundColor: Color, isTop: Boolean = false) {
    val scope = rememberCoroutineScope()

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
            tabs.forEachIndexed { index, tab ->
                LeadingIconTab(
                    icon = {
                        Icon(tab.icon, contentDescription = "")
                    },
                    text = { Text(tab.title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }

                        if (isTop) {
                            MainActivity.state = tab.title.uppercase(Locale.getDefault())
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
                        Icon(tab.icon, contentDescription = "")
                    },
                    text = { Text(tab.title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }

                        if (isTop) {
                            MainActivity.state = tab.title.uppercase(Locale.getDefault())
                        }
                    },
                )
            }
        }
    }
}
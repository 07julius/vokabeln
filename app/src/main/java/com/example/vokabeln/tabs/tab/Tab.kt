package com.example.vokabeln.tabs.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.Tabs
import com.example.vokabeln.tabs.content.TabsContent
import com.example.vokabeln.tabs.item.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tab(tabs: List<TabItem>, backgroundColor: Color, isTop: Boolean = false, topBar: @Composable () -> Unit = {  }) {
    Scaffold(
        topBar = { topBar() },
    ) {
        Column {
            Tabs(tabs = tabs, pagerState = MainActivity.pagerState, backgroundColor, isTop)
            TabsContent(tabs = tabs, pagerState = MainActivity.pagerState)
        }
    }
}
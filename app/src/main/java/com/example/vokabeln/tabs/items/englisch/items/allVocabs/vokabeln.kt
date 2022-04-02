package com.example.vokabeln.tabs.items.englisch.items.allVocabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.item.TabItem
import com.example.vokabeln.tabs.items.englisch.config.AndroidConfig
import com.example.vokabeln.utils.collections.composableRepeating
import com.google.accompanist.pager.ExperimentalPagerApi

var deleting = false

@OptIn(ExperimentalFoundationApi::class)
val vokabeln: TabItem
    @OptIn(ExperimentalPagerApi::class) @Composable get() = TabItem(Icons.Filled.List, "vokabeln") {
        com.example.vokabeln.tabs.tab.Tab(
            composableRepeating(AndroidConfig.instance.vocabs.keys.size) {
                itemBy(it)
            }.plus(allItems()).plus(newTab).toMutableStateList(),
            Color(216, 27, 96),
            pagerState = MainActivity.configKeyPagerState
        )
    }
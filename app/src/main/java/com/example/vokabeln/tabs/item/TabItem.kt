package com.example.vokabeln.tabs.item

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(var icon: ImageVector, var title: String, var screen: @Composable () -> Unit)
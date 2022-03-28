package com.example.vokabeln.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("UNUSED")
object Modifiers {
    val tabItemColumnModifier = Modifier.fillMaxSize().background(Colors.tabItemBackgroundColor).padding(10.dp)
    val tabItemLazyColumnModifier = Modifier.fillMaxSize().background(Colors.tabItemBackgroundColor)

    val tabItemTextFieldModifier = Modifier.fillMaxWidth()
}
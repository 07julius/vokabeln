package com.example.vokabeln.utils.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.example.vokabeln.theme.Colors
import com.example.vokabeln.theme.TextSizes

@Composable
fun TabItemText(text: String, modifier: Modifier = Modifier, fontWeight: FontWeight? = null, fontSize: TextUnit = TextSizes.tabItemTextSize, color: Color? = null, textDecoration: TextDecoration? = null)
        = Text(text, color = color ?: Colors.tabItemContentColor, fontSize = fontSize, modifier = modifier, fontWeight = fontWeight, textDecoration = textDecoration)
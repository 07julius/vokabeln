package com.example.vokabeln.utils.compose

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.vokabeln.theme.TextSizes

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit = TextSizes.tabItemTextSize,
    textDecoration: TextDecoration? = null,
    modifier: Modifier = Modifier
) {
    TabItemText(
        text = text,
        fontWeight = fontWeight,
        fontSize = fontSize,
        textDecoration = textDecoration,
        modifier = modifier
            .weight(weight)
            .padding(8.dp)
    )
}
package com.example.vokabeln.utils.compose

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Modifier.onDoubleClick(delay: Long = 250, onClick: () -> Unit): Modifier {
    var clicked = false
    fun start() = GlobalScope.launch {
        clicked = true
        delay(delay)
        clicked = false
    }

    this.clickable {
        if (clicked) onClick.invoke()
        else start()
    }

    return this
}
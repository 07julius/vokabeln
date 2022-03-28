package com.example.vokabeln.theme

import androidx.compose.ui.unit.sp
import com.example.vokabeln.MainActivity

object TextSizes {
    val topAppBarTextSize = 25.sp * MainActivity.isTablet.toInt()
    val tabItemTextSize = 20.sp * MainActivity.isTablet.toInt()

    val topTableTextSize = 17.5.sp * MainActivity.isTablet.toInt()
    val bottomTableTextSize = 15.sp * MainActivity.isTablet.toInt()

    private fun Boolean.toInt() = if (!this) 1 else 2
}
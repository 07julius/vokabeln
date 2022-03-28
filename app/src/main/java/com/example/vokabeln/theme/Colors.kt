package com.example.vokabeln.theme

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Colors {
    val tabItemBackgroundColor = Color(192,136,121)
    val tabItemContentColor = Color(250, 250, 250)

    val topAppBarBackgroundColor = Color(188, 33, 75)
    val topAppBarContentColor = Color(250, 250, 250)

    val tabItemTextFieldColors: TextFieldColors
        @Composable get() = TextFieldDefaults.textFieldColors(backgroundColor = Color(255, 255, 255, 0) /* textColor = Color.White, disabledTextColor = Color.White */)

    val tabItemButtonColors: ButtonColors
        @Composable get() = ButtonDefaults.buttonColors(backgroundColor = Color(216, 27, 96), contentColor = Color.White)
}
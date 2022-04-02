@file:Suppress("UNUSED")

package com.example.vokabeln.utils.collections

import androidx.compose.runtime.Composable

fun <T> repeating(size: Int, init: (Int) -> T): MutableList<T> = MutableList(size) { init(it) }

@Composable
fun <T> composableRepeating(size: Int, init: @Composable (Int) -> T): MutableList<T> = MutableList(size) { init(it) }

fun <T> repeatingToString(size: Int, init: (Int) -> T): String = MutableList(size) { init(it) }.joinToString { it.toString() }

fun <T, R> List<T>.repeating(init: (T) -> R): MutableList<R> = MutableList(size) { init(get(it)) }
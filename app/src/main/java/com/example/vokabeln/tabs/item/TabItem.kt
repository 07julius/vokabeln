package com.example.vokabeln.tabs.item

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vokabeln.utils.collections.repeating

data class TabItem(var icon: ImageVector?, var title: String, var screen: @Composable () -> Unit) {
    companion object {
        fun from(name: String, screen: @Composable () -> Unit) = TabItem(Icons.Filled.AddBox, name, screen)

        fun fromAll(list: List<String>, screen: @Composable () -> Unit): MutableList<TabItem> = repeating(list.size) { from(list[it], screen) }
    }
}
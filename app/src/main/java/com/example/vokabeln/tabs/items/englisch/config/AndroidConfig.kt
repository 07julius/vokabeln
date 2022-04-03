package com.example.vokabeln.tabs.items.englisch.config

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateList
import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.items.englisch.items.abfrage.Abfrage
import com.example.vokabeln.utils.collections.repeating
import com.example.vokabeln.utils.collections.unpack
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class AndroidConfig private constructor() {
    companion object {
        val instance = AndroidConfig()
    }

    @OptIn(ExperimentalPagerApi::class)
    val key: String
        get() = try {
            vocabs.keys.toList()[MainActivity.configKeyPagerState.currentPage]
        } catch (e: Exception) {
            if (MainActivity.configKeyPagerState.currentPage == vocabs.keys.size) "alle"
            else ""
        }

    @OptIn(ExperimentalPagerApi::class)
    fun keyBy(index: Int) = try {
        vocabs.keys.toList()[index]
    } catch (e: Exception) {
        ""
    }

    fun nextKey(index: Int): Int {
        return if (index >= 1) 0
        else index + 1
    }

    fun getVocabsAtKey(key: String): SnapshotStateList<Vocab>? {
        return if (key != "alle") vocabs[key]
        else vocabs.map { it.value.toMutableStateList() }.unpack().toMutableStateList()
    }

    val vocabs = mutableStateMapOf<String, SnapshotStateList<Vocab>>()
    private lateinit var file: File
    private val json = Json { prettyPrint = true }

    init {
        try {
            val path = MainActivity.instance.applicationContext.filesDir
            val letDirectory = File(path, "LET")
            if (!letDirectory.exists()) {
                letDirectory.mkdirs()
            }
            val oldFile = File(letDirectory, "vocabs.json")
            file = File(letDirectory, "vocabs-sorted.json")
            if (!file.exists()) {
                file.createNewFile()
            }
            vocabs.clear()
            if (oldFile.readText().isNotEmpty()) {
                vocabs["alte"] = json.decodeFromString<MutableList<Vocab>>(oldFile.readText()).toMutableStateList()
            } else {
                vocabs["alte"] = mutableStateListOf()
            }
            vocabs += readVocabs().toStateMap()
            println("vocabs $vocabs")
        } catch (e: Exception) {
            println("error: $e\n occurred during the creation on config")
        }
    }

    private fun readVocabs(): Map<String, MutableList<Vocab>> = try {
        Log.v("readVocabs |", file.readText())
        json.decodeFromString(file.readText())
    } catch (e: Exception) {
        println("$e, happened")
        mapOf()
    }

    fun saveVocabs(datas: Map<String, MutableList<Vocab>> = vocabs) {
        file.writeText(json.encodeToString(datas))
    }

    fun Map<String, MutableList<Vocab>>.toStateMap(): SnapshotStateMap<String, SnapshotStateList<Vocab>> {
        val newValues = values.toMutableList().repeating {
            it.toMutableStateList()
        }
        val newMap = mutableStateMapOf<String, SnapshotStateList<Vocab>>()
        newValues.forEachIndexed { index, _ ->
            newMap[keys.toMutableStateList()[index]] = newValues[index]
        }
        return newMap
    }


    fun update(updateList: Boolean = false, updateMap: Boolean = true) {
        if (updateList) {
            instance.vocabs[instance.key]?.add(Vocab(listOf(), listOf(), -1, -1))
            instance.vocabs[instance.key]?.remove(Vocab(listOf(), listOf(), -1, -1))
        }

        if (updateMap) {
            instance.vocabs[""] = mutableStateListOf(Vocab(listOf(), listOf(), -1, -1))
            instance.vocabs.remove("")
        }
    }

    fun applyVocab(vocab: Vocab, block: Vocab.() -> Unit) {
        (vocabs[key] ?: vocabs.map { it.value }.unpack())[(vocabs[key] ?: vocabs.map { it.value }.unpack()).indexOf(
            vocab
        )].apply(block)
    }
}
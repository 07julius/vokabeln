package com.example.vokabeln.utils.vocabs

import com.example.vokabeln.MainActivity
import com.example.vokabeln.tabs.items.englisch.config.Vocab
import kotlin.random.Random

fun List<Vocab>.getBest(): Vocab? {
    if (isEmpty()) return null
    if (size == 1) return first()
    var best = first()
    shuffled(Random(MainActivity.language.hashCode())).forEach {
        if ((it.guessedRight - it.guessedWrong) > (best.guessedRight - best.guessedWrong)) best = it
    }
    return best
}


fun List<Vocab>.getWorst(): Vocab? {
    if (isEmpty()) return null
    if (size == 1) return first()
    var worst = first()
    shuffled(Random(MainActivity.language.hashCode())).forEach {
        if (it.getScore() > worst.getScore()) worst = it
    }
    return worst
}

fun Vocab.getScore(): Double {
    var score = guessedWrong.toDouble() - guessedRight.toDouble()
    score += wrongPercentage / 10
    return score
}


val Vocab.rightPercentage: Double get() = if (guessedRight == 0 && guessedWrong == 0) 100.0 else (guessedRight.toDouble() / (guessedRight.toDouble() + guessedWrong.toDouble())) * 100

val Vocab.wrongPercentage: Double get() = if (guessedRight == 0 && guessedWrong == 0) 100.0 else (guessedWrong.toDouble() / (guessedRight.toDouble() + guessedWrong.toDouble())) * 100
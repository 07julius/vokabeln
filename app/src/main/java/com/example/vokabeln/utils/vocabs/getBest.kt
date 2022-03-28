package com.example.vokabeln.utils.vocabs

import com.example.vokabeln.tabs.items.englisch.config.Vocab

fun List<Vocab>.getBest(): Vocab? {
    if (isEmpty()) return null
    if (size == 1) return first()
    var best = first()
    forEach {
        if ((it.guessedRight - it.guessedWrong) > (best.guessedRight - best.guessedWrong)) best = it
    }
    return best
}


fun List<Vocab>.getWorst(): Vocab? {
    if (isEmpty()) return null
    if (size == 1) return first()
    var worst = first()
    forEach {
        if (it.getScore() > worst.getScore()) worst = it
        //if ((it.guessedWrong - it.guessedRight) > (worst.guessedWrong - worst.guessedRight)) worst = it
        //if (it.wrongPercentage >= worst.wrongPercentage) worst = it
        //if (it.wrongPercentage - (it.guessedWrong - it.guessedRight) < best.rightPercentage - (best.guessedWrong - best.guessedRight)) best = it
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
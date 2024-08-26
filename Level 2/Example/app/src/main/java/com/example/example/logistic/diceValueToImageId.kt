package com.example.example.logistic

import com.example.example.R

fun diceValueToImageId(diceValue: Int): Int {
    val diceValues: Array<Int> =
        arrayOf(
            R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6
        )
    return diceValues[diceValue-1]
}
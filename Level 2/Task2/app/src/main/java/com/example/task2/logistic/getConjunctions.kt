package com.example.task2.logistic

import com.example.task2.dataClass.Conjunction

fun getConjunctions(): List<Conjunction> {
    return listOf(
        Conjunction("T","T", "T", "T"),
        Conjunction("F","T", "T", "F"),
        Conjunction("T","F", "F", "T"),
        Conjunction("F","F", "F", "T")
    )
}
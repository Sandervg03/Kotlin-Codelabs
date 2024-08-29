package com.example.task2.logistic

import com.example.task2.dataClass.Conjunction

fun processConjunctions(conjunctions: List<Conjunction>, userAnswers: List<String>): Int {
    var correctAnswers: Int = 0
    conjunctions.forEachIndexed { index, conjunction ->
        if (conjunction.answer == userAnswers[index]) {
            correctAnswers++
        }
    }
    return correctAnswers
}
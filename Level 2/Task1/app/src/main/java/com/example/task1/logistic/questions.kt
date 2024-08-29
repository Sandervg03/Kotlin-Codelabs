package com.example.task1.logistic

fun getQuestions(): List<QuestionAnswer> {
    return listOf(
        QuestionAnswer("A val and a var are the same", false),
        QuestionAnswer("Mobile Application Development grants 12 ECTs", false),
        QuestionAnswer("A unit in Kotlin corresponds to a void in Java.", true),
        QuestionAnswer("In Kotlin 'when' replaces the 'switch' operator in Java.", true)
    )
}
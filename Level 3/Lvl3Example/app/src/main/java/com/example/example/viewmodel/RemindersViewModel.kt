package com.example.example.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class RemindersViewModel: ViewModel() {
    private var _reminders: MutableList<String> = mutableStateListOf()

    fun addReminder(newReminder: String) {
        _reminders.add(newReminder)
    }

    fun getReminders(): List<String> {
        return _reminders
    }
}
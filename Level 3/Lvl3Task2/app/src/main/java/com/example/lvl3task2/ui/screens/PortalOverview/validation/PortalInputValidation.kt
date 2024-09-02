package com.example.lvl3task2.ui.screens.PortalOverview.validation

import android.content.Context
import android.webkit.URLUtil
import com.example.lvl3task2.ui.screens.PortalOverview.toast

fun validateInputs(
    name: String,
    url: String,
    context: Context
): Boolean {
    try {
        if (name.isBlank()) {
            throw Error("Name is blank")
        }
        if (url.isBlank()) {
            throw Error("Url is blank")
        }
        if (!url.startsWith("https://")) {
            throw Error("Prefix is not https://")
        }
        if (!URLUtil.isValidUrl(url)) {
            throw Error("Url is invalid")
        }
        return true
    } catch (error: Error) {
        toast(error.message.toString(), context)
        return false
    }
}
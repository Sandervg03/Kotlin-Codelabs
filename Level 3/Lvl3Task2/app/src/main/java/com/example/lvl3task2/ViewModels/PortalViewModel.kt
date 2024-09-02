package com.example.lvl3task2.ViewModels

import android.webkit.URLUtil
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class PortalViewModel: ViewModel() {
    private var _portalName: SnapshotStateList<String> = mutableStateListOf()
    private var _portalUrl: SnapshotStateList<String> = mutableStateListOf()

    fun addPortalName(name: String) {
        if (name.isBlank()) {
            throw Error("Name is blank")
        }
        _portalName.add(name)
    }

    fun getPortalNames(): List<String> {
        return _portalName
    }

    fun addPortalUrl(url: String) {
        if (url.isBlank()) {
            throw Error("Url is blank")
        }
        if (!url.startsWith("https://")) {
            throw Error("Prefix is not https://")
        }
        if (!URLUtil.isValidUrl(url)) {
            throw Error("Url is invalid")
        }
        _portalUrl.add(url)
    }

    fun getPortalUrls(): List<String> {
        return _portalUrl
    }
}
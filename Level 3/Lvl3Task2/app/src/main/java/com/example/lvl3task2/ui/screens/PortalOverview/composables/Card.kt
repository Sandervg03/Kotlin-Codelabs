package com.example.lvl3task2.ui.screens.PortalOverview.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lvl3task2.MainActivity

@Composable
fun Card(name: String, url: String, context: Context) {
    Column(
        modifier = Modifier
            .clickable {
                var intent: CustomTabsIntent = CustomTabsIntent.Builder().build()
                intent.launchUrl(context, Uri.parse(url))
            }
            .clip(shape = RoundedCornerShape(corner = CornerSize(15.dp)))
            .background(color = Color.LightGray)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = name, modifier = Modifier.padding(10.dp))
        Text(text = url, modifier = Modifier.padding(10.dp))
    }
}
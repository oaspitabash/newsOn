package com.news.newson.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun FlashScreen(onNavigate: (isFirstTime: Boolean) -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000) // splash delay
        val firstTime = true // fetch from DataStore/SharedPref
        onNavigate(firstTime)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Flash Screen")
    }
}
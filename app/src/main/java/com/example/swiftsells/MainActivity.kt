package com.example.swiftsells

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.swiftsells.navigation.SwiftNavigation
import com.example.swiftsells.ui.theme.MainBgColor
import com.example.swiftsells.ui.theme.SwiftSellsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwiftSellsTheme {
                Surface(
                    color = MainBgColor,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(verticalArrangement =  Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        SwiftNavigation()
                    }
                }
            }
        }
    }
}



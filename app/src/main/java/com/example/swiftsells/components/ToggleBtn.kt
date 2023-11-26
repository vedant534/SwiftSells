package com.example.swiftsells.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.swiftsells.ui.theme.BgGrey
import com.example.swiftsells.ui.theme.MainBgColor
import com.example.swiftsells.ui.theme.SecondaryColor

@Composable
fun ToggleBtn(label: String, onState: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier.height(37.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (onState) SecondaryColor else BgGrey,
            contentColor = if (onState) MainBgColor else Color.White,
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = label)
        }
    }
}
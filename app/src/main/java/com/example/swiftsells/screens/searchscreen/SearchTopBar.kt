package com.example.swiftsells.screens.searchscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.swiftsells.ui.theme.MainBgColor
import com.example.swiftsells.ui.theme.SecondaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(scrollBehavior: TopAppBarScrollBehavior, navController: NavHostController) {
    val topColor =
        TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MainBgColor,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White,
        )

    TopAppBar(
        title = {
            Row{
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Search", fontWeight = FontWeight.Bold, fontSize = 25.sp)
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()  }){
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = null,
                    tint = SecondaryColor
                )
            }
        },
        colors = topColor,
        scrollBehavior = scrollBehavior
    )
}
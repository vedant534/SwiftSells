package com.example.swiftsells.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.swiftsells.navigation.SwiftScreens
import com.example.swiftsells.ui.theme.MainBgColor
import com.example.swiftsells.ui.theme.SecondaryColor

@Composable
fun BottomAppBarWithFAB(isHome: Boolean, navController: NavHostController) {

    BottomAppBar(
        actions = {
            //home btn

            Button(
                onClick = { if(!isHome) navController.navigate(SwiftScreens.HomeScreen.name)  },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(isHome) SecondaryColor else MainBgColor,
                    contentColor = if(isHome) MainBgColor else SecondaryColor
                )
            ) {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(32.dp),
                    )
                    Text(text = "HOME", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.width(25.dp))

            //search btn

            Button(
                onClick = { if(isHome) navController.navigate(SwiftScreens.SearchScreen.name) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(!isHome) SecondaryColor else MainBgColor,
                    contentColor = if(!isHome) MainBgColor else SecondaryColor
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "search",
                        modifier = Modifier.size(32.dp),
                    )
                    Text(text = "SEARCH", fontSize = 12.sp)
                }

            }
        },
        containerColor = MainBgColor,
        contentColor = SecondaryColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(SwiftScreens.AddScreen.name) },
                containerColor = SecondaryColor,
                contentColor = MainBgColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(Icons.Filled.Add, "FAB")
                    Text(text = "ADD", fontSize = 12.sp)
                }

            }
        }
    )
}
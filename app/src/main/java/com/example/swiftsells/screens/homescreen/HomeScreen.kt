package com.example.swiftsells.screens.homescreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.swiftsells.components.BottomAppBarWithFAB
import com.example.swiftsells.components.ProductCard
import com.example.swiftsells.components.ToggleBtn
import com.example.swiftsells.model.ProductDetails
import com.example.swiftsells.ui.theme.MainBgColor
import com.example.swiftsells.ui.theme.SecondaryColor
import com.example.swiftsells.ui.theme.TernaryGreen

//@Preview
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val listOfInterest = viewModel.list //list containing our items
    var filteredList by rememberSaveable {
        mutableStateOf(listOfInterest)
    }
    filteredList = listOfInterest //integrate
    val topAppBarColor =
        TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MainBgColor,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White,
        )
    Scaffold(
        modifier = Modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {

            var currentOn by rememberSaveable {
                mutableIntStateOf(0)
            }

            val btnList = listOf<String>(
                "All",
                "Clothing",
                "Product",
                "Essential",
                "Mobile",
                "Grocery",
                "Furniture"
            )

            TopAppBar(
                title = {
                    LazyRow {
                        btnList.forEachIndexed { idx, it ->
                            item {
                                Row {
                                    ToggleBtn(label = it, currentOn == idx, onClick = {
                                        currentOn = idx
                                        Log.d("myTag", "clickable clicked")
                                        filteredList = if (idx == 0) listOfInterest
                                        else listOfInterest.filter { listItem ->
                                            listItem
                                                .toString().toLowerCase()
                                                .contains(it.toLowerCase())
                                        } as ArrayList<ProductDetails>

                                    }
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                }
                            }
                        }

                    }
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            modifier = Modifier.size(45.dp),
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            tint = TernaryGreen
                        )
                    }
                },
                colors = topAppBarColor,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomAppBarWithFAB(true, navController)
        },
    ) { innerPadding ->

        //Scaffold Content

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MainBgColor
        ) {
            if (filteredList.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    filteredList.forEachIndexed { idx, it ->
                        item() {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(420.dp)
                            ) {
                                Box(modifier = Modifier.weight(1F)) {
                                    ProductCard(it)
                                }
                                Box(modifier = Modifier.weight(1F)) {
                                    if (idx + 1 < filteredList.size) ProductCard(filteredList[idx + 1])
                                }
                            }
                        }

                    }
                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.padding(top = 300.dp, start = 160.dp, end = 160.dp),
                    color = Color.DarkGray,
                    strokeWidth = 8.dp,
                    trackColor = SecondaryColor
                )
            }
        }
    }
}


package com.example.swiftsells.screens.searchscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.swiftsells.components.BottomAppBarWithFAB
import com.example.swiftsells.components.ProductCard
import com.example.swiftsells.model.ProductDetails
import com.example.swiftsells.ui.theme.MainBgColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {

    //variables
    val viewModel = hiltViewModel<SearchScreenViewModel>()
    val listOfInterest = viewModel.list
    var filteredList by rememberSaveable {
        mutableStateOf(listOfInterest)
    }
    filteredList = listOfInterest //???
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            SearchTopBar(scrollBehavior, navController)
        },
        bottomBar = {
            BottomAppBarWithFAB(false, navController)
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MainBgColor
        ) {
            //Scaffold content

            Box(
                Modifier
                    .fillMaxSize()
                    .semantics { isTraversalGroup = true }) {

                //Search Bar
                DockedSearchBar(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 8.dp)
                        .semantics { traversalIndex = -1f },
                    query = text,
                    onQueryChange = {
                        text = it
                        filteredList = listOfInterest.filter { listItem ->
                            listItem.toString().toLowerCase().contains(text.toLowerCase())
                        } as ArrayList<ProductDetails>
                    },
                    onSearch = { active = false },
                    active = active,
                    onActiveChange = { active = it },
                    placeholder = { Text("Search for Products, Brands and More") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                ) {

                    //Recommendations

                    val listOfSearchRecommendations =
                        listOf("All", "Type A", "Electronics", "Essentials")
                    listOfSearchRecommendations.forEachIndexed { idx, it ->
                        val resultText = it
                        ListItem(
                            headlineContent = { Text(it) },
                            leadingContent = {
                                Icon(
                                    Icons.Filled.Lightbulb,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier
                                .clickable {
                                    text = if (idx == 0) "" else it
                                    filteredList = listOfInterest.filter { listItem ->
                                        listItem
                                            .toString()
                                            .contains(text)
                                    } as ArrayList<ProductDetails>
                                    active = false
                                }
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                }

                //Search Result Content
                if (filteredList.isNotEmpty()) {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            top = 72.dp,
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        filteredList.forEachIndexed { idx, it ->
                            item() {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(420.dp)) {
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
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { Text(text = "does not exist", color = Color.White) }
                }
            }
        }
    }
}
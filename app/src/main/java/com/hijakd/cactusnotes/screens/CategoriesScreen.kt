package com.hijakd.cactusnotes.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    var canAddNewCategory by remember { mutableStateOf(false) }

    Scaffold(modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text("Categories") },
            /*navigationIcon = {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    modifier = modifier.padding(5.dp).clickable{TODO()} ,
                    contentDescription = "back arrow",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },*/
            actions = {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "add new category",
                    modifier
                            .padding(end = 15.dp)
                            .size(37.dp)
                            .clickable { canAddNewCategory = !canAddNewCategory },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) // END of TopAppBar
    }) {
        Column(modifier.padding(it)) {
            Text("Blah")
        }
    } // END of Scaffold
}

@Preview(showBackground = true)
@Composable
fun ShowCategoriesScreen() {
    val navController = rememberNavController()
    CategoriesScreen(navController = navController)
}
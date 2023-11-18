package com.example.templateapplication.screens.appBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTopAppBar(currentpage: String,  navigationIcon: @Composable () -> Unit) {
    TopAppBar(
        title = {
            Text(text = currentpage)
        },
        navigationIcon = navigationIcon
    )
}
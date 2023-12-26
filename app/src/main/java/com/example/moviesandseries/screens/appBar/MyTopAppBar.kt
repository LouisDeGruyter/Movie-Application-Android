package com.example.templateapplication.screens.appBar

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTopAppBar(currentpage: String, navigationIcon: @Composable () -> Unit) {
    TopAppBar(
        title = {
            Text(text = currentpage, fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
        },
        modifier = Modifier.heightIn(30.dp, 40.dp),
        navigationIcon = navigationIcon,
    )
}

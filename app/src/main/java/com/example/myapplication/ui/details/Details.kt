package com.example.myapplication.ui.details

import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column

@Composable
fun Details(navController: NavController, itemId: String?) {
    Column {
        Text( "Details Screen - Item ID: $itemId ")
        Button(onClick = { navController.popBackStack() }) {
            Text("Go back")
        }
    }
}
package com.example.myapplication.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.myapplication.ui.components.BottomMenuWithFab


@Composable
fun Home(navController: NavController){
    BottomMenuWithFab(
        navController = navController,
        content = { paddingValues ->
            // Tu contenido principal aquí
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Contenido de la pantalla principal")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(navController = NavController(context = LocalContext.current))
}
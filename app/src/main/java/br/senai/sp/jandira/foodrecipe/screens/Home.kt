package br.senai.sp.jandira.foodrecipe.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navegacao: NavHostController?
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ){}
}

@Preview
@Composable
private fun HomeScreenPreview(){
    HomeScreen(null)
}
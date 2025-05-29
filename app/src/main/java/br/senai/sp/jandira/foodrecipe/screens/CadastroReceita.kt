package br.senai.sp.jandira.foodrecipe.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CadastroReceita(
    navegacao: NavHostController?
){
    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(color = Color.Cyan)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            colors = CardDefaults.cardColors(Color(0xFFFFDF87)),
            shape = RoundedCornerShape(0.dp)

        ){
            Row {  }
        }
        Card(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
                .height(100.dp),
            colors = CardDefaults.cardColors(Color(0xCC241508))
        ) { }
    }


}

@Preview
@Composable
private fun CadastroReceitaPreview(){
    CadastroReceita(null)
}
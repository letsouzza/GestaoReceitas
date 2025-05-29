package br.senai.sp.jandira.foodrecipe.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.foodrecipe.R
import br.senai.sp.jandira.foodrecipe.ui.theme.podkovaFamily
import br.senai.sp.jandira.foodrecipe.ui.theme.rethinkFamily

@Composable
fun CadastroReceita(
    navegacao: NavHostController?
){
    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(color = Color(0xFF261C09))
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            colors = CardDefaults.cardColors(Color(0xFFFFDF87)),
            shape = RoundedCornerShape(0.dp)

        ){
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.logoblack),
                    contentDescription = "",
                    modifier = Modifier
                        .size(90.dp)
                )
                IconButton(
                    onClick = {}
                ){
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxHeight()
                            .size(120.dp),
                        tint = Color(0xFF261C09)
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .padding(top = 110.dp)
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .height(710.dp),
            colors = CardDefaults.cardColors(Color(0xFFF8D99E))
        ){
            Row(
                modifier = Modifier
                    .padding(top = 30.dp, start = 15.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Fastfood,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF261C09)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.register_receipe),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = rethinkFamily,
                    color = Color(0xFF241A06)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(R.string.firt_step),
                    fontSize = 20.sp,
                    fontFamily = podkovaFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xC9241A06),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CadastroReceitaPreview(){
    CadastroReceita(null)
}
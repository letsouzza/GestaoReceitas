package br.senai.sp.jandira.homereceita.screens.model

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun CardHome(

    img: String = "",
    texto1: String = "",
    texto2: String = "",
    texto3: String = ""
) {
    Card (
        modifier = Modifier
            .height(170.dp)
            .width(370.dp)
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ){
        Column (
            modifier = Modifier
                .background(
                    color = Color(0XFFF8D99E)
                )
                .fillMaxSize()


        ){
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(120.dp)
                        .background(
                            color = Color.Transparent
                        )

                        .border(
                            width = 2.dp,
                            color = Color(0xff261C09),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxSize(),
                        colors = CardDefaults.cardColors(Color.Magenta)
                    ){
                        AsyncImage(
                            model = img,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(250.dp)
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween

                ){
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()

                    ){
                        Text(
                            text = texto1,
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }

                    Text(
                        text = texto2,
                        color = Color.Black,
                        fontSize = 14.sp,
                        modifier = Modifier

                    )
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = "",
                            modifier = Modifier
                                .size(25.dp)
                        )
                        Text(
                            text = texto3,
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier

                        )
                    }
                }
            }
        }
    }
}


@Preview()
@Composable
private fun CardCoursesPreview(){
   CardHome()
}


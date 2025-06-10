package br.senai.sp.jandira.homereceita.screens

import android.util.Log
import androidx.compose.material.icons.filled.List
import br.senai.sp.jandira.homereceita.screens.model.CardHome
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.foodrecipe.R
import br.senai.sp.jandira.foodrecipe.model.ReceitaList
import br.senai.sp.jandira.foodrecipe.model.ResultReceita
import br.senai.sp.jandira.foodrecipe.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun HomeScreen(
    navegacao: NavHostController?
) {
    var search = remember {
        mutableStateOf("")
    }

    val expandedMenu = remember { mutableStateOf(false) }

    var listaReceita = remember {
        mutableStateOf(listOf<ReceitaList>())
    }

    // Obter um Retrofit Factory
    var callReceita = RetrofitFactory()
        .getReceipeService()
        .mostrarReceita()
    Log.d("teste" , "$listaReceita")

    // Enviar a requisição
    // enqueue- enviar
    // Retorna um Result
    callReceita.enqueue(object : Callback<ResultReceita>{
        override fun onResponse(p0: Call<ResultReceita>, response: Response<ResultReceita>) {
            listaReceita.value = response.body()!!.receitas
        }

        override fun onFailure(p0: Call<ResultReceita>, p1: Throwable) {
            Log.d("erro" , "erro")
            Log.d("teste1" , "$listaReceita")
        }
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(0xff261C09)
                ),

            ){
                Column(
                modifier = Modifier
                    .height(110.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(0xffFFDF87)
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(80.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                R.drawable.logoblack
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center

                    ) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = expandedMenu.value,
                        onDismissRequest = { expandedMenu.value = false },
                        modifier = Modifier
                            .background(Color(0xFFFFDF87))
                            .wrapContentWidth()
                    ) {
                        DropdownMenuItem(
                            text = { Text("Home") },
                            onClick = { navegacao?.navigate("home") }
                        )
                        DropdownMenuItem(
                            text = { Text("Perfil") },
                            onClick = { }
                        )
                        DropdownMenuItem(
                            text = { Text("Cadastrar Receita") },
                            onClick = { navegacao?.navigate("cadastroReceita") }
                        )
                        DropdownMenuItem(
                            text = { Text("Favoritas") },
                            onClick = { }
                        )
                    }
                }
            }
            OutlinedTextField(
                value = search.value, // Use the state variable directly
                onValueChange = { search.value = it },
                modifier = Modifier
                    .padding(top = 20.dp, end = 20.dp, start = 20.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Go
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color(0XFFF8D99E),
                        modifier = Modifier
                            .size(40.dp)
                    )
                },
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors( // <-- Use OutlinedTextFieldDefaults.colors()
                    focusedBorderColor = Color(0XFFF8D99E),
                    unfocusedBorderColor = Color(0XFFF8D99E),
                    focusedTextColor = Color(0XFFF8D99E),
                    unfocusedTextColor = Color(0XFFF8D99E)
                ),
                label = {
                    Text(
                        text = "",
                        color = Color(0XFF9E9FA4)
                    )
                }
            )
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn {
                    Log.d("teste2" , "${listaReceita.value}")
                    items(listaReceita.value){
                        CardHome(
                            img = it.imagem,
                            texto1 = it.titulo,
                            texto2 = it.descricao,
                            texto3 = it.tempo
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CoursesScreenPreview(){
    HomeScreen(null)
}
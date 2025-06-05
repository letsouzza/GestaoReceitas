package br.senai.sp.jandira.foodrecipe.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.foodrecipe.R
import br.senai.sp.jandira.foodrecipe.model.DifficultLevel
import br.senai.sp.jandira.foodrecipe.model.ResultLevel
import br.senai.sp.jandira.foodrecipe.screens.components.NivelDropdown
import br.senai.sp.jandira.foodrecipe.service.RetrofitFactory
import br.senai.sp.jandira.foodrecipe.ui.theme.podkovaFamily
import br.senai.sp.jandira.foodrecipe.ui.theme.poppinsFamily
import br.senai.sp.jandira.foodrecipe.ui.theme.rethinkFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CadastroReceita(
    navegacao: NavHostController?,
) {

    var levelList by remember { mutableStateOf(listOf<DifficultLevel>()) }

    // Obter um Retrofit Factory
    var callLevel = RetrofitFactory()
        .getLevelService()
        .listLevel()

    // Enviar a requisição
    // enqueue- enviar
    // Retorna um Result
    callLevel.enqueue(object : Callback<ResultLevel> {
        override fun onResponse(p0: Call<ResultLevel>, response: Response<ResultLevel>) {
            levelList = response.body()!!.nivel
            Log.d("oi", "funcionou")
        }

        override fun onFailure(p0: Call<ResultLevel>, p1: Throwable) {
            Log.e("erro", "erro")
        }

    })

    val expandedMenu = remember { mutableStateOf(false) }

    var nivelSelecionadoId by remember { mutableStateOf<Int?>(null) }

    var nameRecipeState = remember {
        mutableStateOf("")
    }
    var descriptionState = remember {
        mutableStateOf("")
    }
    var timeState = remember {
        mutableStateOf("")
    }
    var porcoesState = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val userFile = context.getSharedPreferences("user_file", Context.MODE_PRIVATE)
    val editor = userFile.edit()

    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(color = Color(0xFF261C09))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            colors = CardDefaults.cardColors(Color(0xFFFFDF87)),
            shape = RoundedCornerShape(0.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.logoblack),
                    contentDescription = "",
                    modifier = Modifier
                        .size(90.dp)
                )
                Box {
                    IconButton(
                        onClick = { expandedMenu.value = !expandedMenu.value }
                    ) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "",
                            tint = Color(0xFF261C09),
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = expandedMenu.value,
                        onDismissRequest = { expandedMenu.value = false },
                        modifier = Modifier
                            .background(Color(0xFFFFDF87))
                            .wrapContentWidth()
                            .align(Alignment.TopEnd) // Só funciona dentro BoxScope!
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
        }
        Card(
            modifier = Modifier
                .padding(top = 110.dp)
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .height(720.dp),
            colors = CardDefaults.cardColors(Color(0xCCF8D99E))
        ) {
            Column {
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
                ) {
                    Text(
                        text = stringResource(R.string.firt_step),
                        fontSize = 20.sp,
                        fontFamily = podkovaFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xC9241A06),
                        modifier = Modifier.padding(top = 25.dp, start = 30.dp)
                    )
                    OutlinedTextField(
                        value = nameRecipeState.value,
                        onValueChange = { nameRecipeState.value = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x80241508),
                            focusedContainerColor = Color(0x80241508),
                            unfocusedBorderColor = Color(0xFF261C09),
                            focusedBorderColor = Color(0xFF261C09),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(35.dp),
                        label = {
                            Text(
                                text = stringResource(
                                    R.string.name_receipe
                                ),
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                fontFamily = poppinsFamily,
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 35.dp)
                    )
                    OutlinedTextField(
                        value = descriptionState.value,
                        onValueChange = { descriptionState.value = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x80241508),
                            focusedContainerColor = Color(0x80241508),
                            unfocusedBorderColor = Color(0xFF261C09),
                            focusedBorderColor = Color(0xFF261C09),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(25.dp),
                        label = {
                            Text(
                                text = stringResource(
                                    R.string.description
                                ),
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                fontFamily = poppinsFamily,
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .padding(horizontal = 10.dp)
                            .padding(top = 15.dp)
                    )
                    OutlinedTextField(
                        value = timeState.value,
                        onValueChange = { timeState.value = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x80241508),
                            focusedContainerColor = Color(0x80241508),
                            unfocusedBorderColor = Color(0xFF261C09),
                            focusedBorderColor = Color(0xFF261C09),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(35.dp),
                        label = {
                            Text(
                                text = stringResource(
                                    R.string.time
                                ),
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                fontFamily = poppinsFamily,
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 15.dp)
                    )
                    OutlinedTextField(
                        value = porcoesState.value,
                        onValueChange = { porcoesState.value = it },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x80241508),
                            focusedContainerColor = Color(0x80241508),
                            unfocusedBorderColor = Color(0xFF261C09),
                            focusedBorderColor = Color(0xFF261C09),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(35.dp),
                        label = {
                            Text(
                                text = stringResource(
                                    R.string.porcoes
                                ),
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                fontFamily = poppinsFamily,
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 15.dp)
                    )
                    Column {
                        NivelDropdown(
                            niveis = levelList,
                            onNivelSelecionado = { id ->
                                nivelSelecionadoId = id
                            }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 40.dp, end = 10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                editor.putString("titulo", nameRecipeState.value)
                                editor.putString("descricao", descriptionState.value)
                                editor.putString("tempo_preparo", timeState.value)
                                editor.putString("porcoes", porcoesState.value)
                                editor.putInt("id_nivel_dificuldade", nivelSelecionadoId ?: 0)
                                editor.apply()
                                navegacao?.navigate("cadastroIngrediente")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "",
                                tint = Color(0xFF261C09),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CadastroReceitaPreview() {
    CadastroReceita(null)
}
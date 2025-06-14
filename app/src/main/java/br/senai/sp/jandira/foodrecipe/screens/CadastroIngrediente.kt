package br.senai.sp.jandira.foodrecipe.screens

import android.content.Context
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
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
import br.senai.sp.jandira.foodrecipe.model.Categoria
import br.senai.sp.jandira.foodrecipe.model.ResultCategoria
import br.senai.sp.jandira.foodrecipe.screens.components.CategoryCheck
import br.senai.sp.jandira.foodrecipe.service.RetrofitFactory
import br.senai.sp.jandira.foodrecipe.ui.theme.podkovaFamily
import br.senai.sp.jandira.foodrecipe.ui.theme.poppinsFamily
import br.senai.sp.jandira.foodrecipe.ui.theme.rethinkFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CadastroIngrediente(
    navegacao: NavHostController?
){
    val expandedMenu = remember { mutableStateOf(false) }

    var categoryList by remember { mutableStateOf(listOf<Categoria>()) }

    val checkedStates = remember { mutableStateMapOf<Int, Boolean>() }

    // Obter um Retrofit Factory
    var callCategory = RetrofitFactory()
        .getCategoryService()
        .listCategoria()

    // Enviar a requisição
    // enqueue- enviar
    // Retorna um Result
    callCategory.enqueue(object : Callback<ResultCategoria> {
        override fun onResponse(p0: Call<ResultCategoria>, response: Response<ResultCategoria>) {
            categoryList = response.body()!!.categorias
        }
        override fun onFailure(p0: Call<ResultCategoria>, p1: Throwable) {
            TODO("Not yet implemented")
        }
    })

    var ingredienteState = remember {
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
                            onClick = { navegacao?.navigate("home1") }
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
                .height(720.dp),
            colors = CardDefaults.cardColors(Color(0xCCF8D99E))
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                Text(
                    text = stringResource(R.string.second_step),
                    fontSize = 20.sp,
                    fontFamily = podkovaFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xC9241A06),
                    modifier = Modifier.padding(top = 25.dp, start = 30.dp)
                )
                Text(
                    text = stringResource(
                        R.string.category
                    ),
                    fontSize = 25.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF261C09),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .padding(top = 35.dp)
                )
                Column {
                    categoryList.forEach { categoria ->
                        val isChecked = checkedStates[categoria.id] ?: false

                        CategoryCheck(
                            checkedText = categoria.categoria,
                            check = isChecked,
                            onCategoriaSelecionada = {
                                checkedStates[categoria.id] = !isChecked
                            }
                        )
                    }
                }
                OutlinedTextField(
                    value = ingredienteState.value,
                    onValueChange = { ingredienteState.value = it},
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
                                R.string.ingredients
                            ),
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            fontFamily = poppinsFamily,
                            color = Color.White
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(330.dp)
                        .padding(horizontal = 10.dp)
                        .padding(top = 15.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ){
                    IconButton(
                        onClick = {navegacao?.navigate("cadastroReceita")}
                    ){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = Color(0xFF261C09),
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            // Filtrar apenas os IDs checados
                            val categoriasSelecionadas = checkedStates.filter { it.value }.keys

                            // Salvar como string separada por vírgulas (ex: "1,2,3")
                            editor.putString("categorias", categoriasSelecionadas.joinToString(","))
                            editor.putString("ingredientes", ingredienteState.value)
                            editor.apply()
                            navegacao?.navigate("cadastroFinal")
                        }
                    ){
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

@Preview
@Composable
private fun CadastroIngredientePreview(){
    CadastroIngrediente(null)
}
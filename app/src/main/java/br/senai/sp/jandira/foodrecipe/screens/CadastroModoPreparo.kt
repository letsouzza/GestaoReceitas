package br.senai.sp.jandira.foodrecipe.screens

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import br.senai.sp.jandira.foodrecipe.model.Categoria
import br.senai.sp.jandira.foodrecipe.model.ReceitaRegister
import br.senai.sp.jandira.foodrecipe.model.ResponsePost
import br.senai.sp.jandira.foodrecipe.service.AzureUploadService.uploadImageToAzure
import br.senai.sp.jandira.foodrecipe.service.RetrofitFactory
import br.senai.sp.jandira.foodrecipe.ui.theme.podkovaFamily
import br.senai.sp.jandira.foodrecipe.ui.theme.poppinsFamily
import br.senai.sp.jandira.foodrecipe.ui.theme.rethinkFamily
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun CadastroModoPreparo(
    navegacao: NavHostController?
){
    val expandedMenu = remember { mutableStateOf(false) }
    val expandedDificuldade = remember { mutableStateOf(false) }

    var preparationState = remember {
        mutableStateOf("")
    }
    var imageState = remember {
        mutableStateOf("")
    }


    // 1) Estado para armazenar o URI da imagem escolhida
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // 2) Estado para armazenar a URL retornada pelo Azure
    var imageUrl by remember { mutableStateOf<String?>(null) }

    // 3) Launcher para pegar o arquivo via Galeria
    val pickImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageUri = uri
        }

    val context = LocalContext.current
    val userFile = context.getSharedPreferences("user_file", Context.MODE_PRIVATE)
    val titulo = userFile.getString("titulo", "")
    val ingredientes = userFile.getString("ingredientes", "")
    val porcoes = userFile.getString("porcoes", "")
    val tempo = userFile.getString("tempo_preparo", "")
    val descricao = userFile.getString("descricao", "")
    val nivel = userFile.getInt("id_nivel_dificuldade" , 1)

    val categoriasString = userFile.getString("categorias", "") ?: ""
    val categoriasList = categoriasString.split(",").mapNotNull { it.toIntOrNull() }


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
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.third_step),
                        fontSize = 20.sp,
                        fontFamily = podkovaFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xC9241A06),
                        modifier = Modifier.padding(top = 25.dp, start = 30.dp)
                    )
                    OutlinedTextField(
                        value = preparationState.value,
                        onValueChange = { preparationState.value = it },
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
                                    R.string.preparation_method
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
                            .padding(top = 50.dp)
                    )
                    OutlinedTextField(
                        value = imageState.value,
                        onValueChange = { },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFFFD972),
                            focusedContainerColor = Color(0xFFFFD972),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(35.dp),
                        label = {
                            Text(
                                text = stringResource(
                                    R.string.image_receipe
                                ),
                                fontSize = 20.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF261C09)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = "",
                                tint = Color(0xFF261C09),
                                modifier = Modifier
                                    .padding(start = 15.dp)
                                    .size(40.dp)
                            )
                        },
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 20.dp)
                            .clickable {
                                pickImageLauncher.launch("image/*")
                            }
                    )
                    imageUri?.let { uri ->
                        AsyncImage(
                            model = uri,
                            contentDescription = "Imagem Selecionada",
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 15.dp)
                                .fillMaxWidth()
                                .border(2.dp, Color(0x80241508))
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 20.dp)
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        IconButton(
                            onClick = { navegacao?.navigate("cadastroIngredientes") }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                tint = Color(0xFF261C09),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        Button(
                            onClick = {
                                // 1) Executa a rotina de upload em background
                                CoroutineScope(Dispatchers.IO).launch {
                                    try {
                                        // 2) Chama a sua função de upload e aguarda a URL
                                        val urlRetornada = uploadImageToAzure(context, imageUri!!)

                                        val categoriasJsonList = categoriasList.map { Categoria(it) }

                                        val body = ReceitaRegister(
                                            titulo = "$titulo",
                                            descricao = "$descricao",
                                            modoPreparo = preparationState.value,
                                            imagem = "$urlRetornada",
                                            ingredientes = "$ingredientes",
                                            tempo = "$tempo",
                                            porcoes = "$porcoes",
                                            idUser = 47,
                                            nivelDificuldade = nivel,
                                            categoria = categoriasJsonList
                                        )
                                        Log.d("lara", "$body")

                                        val sendReceita = RetrofitFactory()
                                            .getReceipeService()
                                            .insertReceita(body)

                                        sendReceita.enqueue(object : Callback<ResponsePost> {
                                            override fun onResponse(
                                                call: Call<ResponsePost>,
                                                response: Response<ResponsePost>
                                            ) {
                                                if (response.isSuccessful) {
                                                    Toast.makeText(
                                                        context,
                                                        "Cadastro OK: ${response.body()?.message}",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                    navegacao?.navigate("home1")
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Erro no servidor: código ${response.code()}",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            }

                                            override fun onFailure(p0: Call<ResponsePost>, p1: Throwable) {
                                                Log.e("Erro", "Não foi possível cadastrar")
                                            }
                                        })
                                        // 3) Volta na Main e guarda a URL num estado para usar depois
                                        withContext(Dispatchers.Main) {
                                            imageUrl = urlRetornada
                                        }
                                    } catch (e: Exception) {
                                        Log.e("ExemploEnvio", "Falha no upload: ${e.message}")
                                        withContext(Dispatchers.Main) {
                                            Toast.makeText(context, "Erro no upload", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            },
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFFFDF87))
                        ) {
                            Text(
                                text = stringResource(R.string.register),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = poppinsFamily,
                                color = Color(0xFF261C09)
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
private fun CadastroModoPreparoPreview(){
    CadastroModoPreparo(null)
}
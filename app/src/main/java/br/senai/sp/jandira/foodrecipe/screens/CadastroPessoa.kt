package br.senai.sp.jandira.foodrecipe.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import br.senai.sp.jandira.foodrecipe.R
import br.senai.sp.jandira.foodrecipe.model.ResponsePost
import br.senai.sp.jandira.foodrecipe.model.UserRegister
import br.senai.sp.jandira.foodrecipe.service.RetrofitFactory
import br.senai.sp.jandira.foodrecipe.ui.theme.poppinsFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun CadastroPessoa(
    navegacao: NavHostController?
){
    val context = LocalContext.current

    val senhaVisivel = remember { mutableStateOf(false) }
    val palavraVisivel = remember { mutableStateOf(false) }

    var nameState = remember {
        mutableStateOf("")
    }
    var emailState = remember {
        mutableStateOf("")
    }
    var passwordState = remember {
        mutableStateOf("")
    }
    var keywordState = remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painterResource(R.drawable.background1),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0x4D000000))
        ){
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 43.dp, horizontal = 23.dp),
                colors = CardDefaults.cardColors(Color(0xCC241508))
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    Row(
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .fillMaxWidth()
                            .height(160.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(R.drawable.logowhite),
                            contentDescription = "",
                            modifier = Modifier
                                .size(150.dp)
                        )
                        Text(
                            text = stringResource(R.string.register),
                            fontSize = 33.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = poppinsFamily,
                            color = Color.White
                        )
                    }
                    OutlinedTextField(
                        value = nameState.value,
                        onValueChange = { it ->
                            nameState.value = it
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x85715F3E),
                            focusedContainerColor = Color(0x85715F3E),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(60.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(25.dp)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(
                                    R.string.name
                                ),
                                fontSize = 20.sp,
                                fontFamily = poppinsFamily,
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 25.dp)
                    )
                    OutlinedTextField(
                        value = emailState.value,
                        onValueChange = { it ->
                            emailState.value = it
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x85715F3E),
                            focusedContainerColor = Color(0x85715F3E),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(60.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(25.dp)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(
                                    R.string.email
                                ),
                                fontSize = 20.sp,
                                fontFamily = poppinsFamily,
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 25.dp)
                    )
                    OutlinedTextField(
                        value = passwordState.value,
                        onValueChange = { it ->
                            passwordState.value = it
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x85715F3E),
                            focusedContainerColor = Color(0x85715F3E),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(60.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(25.dp)
                            )
                        },
                        trailingIcon = {
                            val icon = if (senhaVisivel.value) Icons.Default.Visibility else Icons.Default.VisibilityOff

                            IconButton(onClick = { senhaVisivel.value = !senhaVisivel.value }) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        },
                        visualTransformation =
                            if (senhaVisivel.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                        label = {
                            Text(
                                text = stringResource(
                                    R.string.password
                                ),
                                fontSize = 20.sp,
                                fontFamily = poppinsFamily,
                                color = Color.White,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 25.dp)
                            .zIndex(1f)
                    )
                    OutlinedTextField(
                        value = keywordState.value,
                        onValueChange = { it ->
                            keywordState.value = it
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x85715F3E),
                            focusedContainerColor = Color(0x85715F3E),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(60.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Key,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(25.dp)
                            )
                        },
                        trailingIcon = {
                            val icon = if (palavraVisivel.value) Icons.Default.Visibility else Icons.Default.VisibilityOff

                            IconButton(onClick = { palavraVisivel.value = !palavraVisivel.value }) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        },
                        visualTransformation =
                            if (palavraVisivel.value) VisualTransformation.None
                            else PasswordVisualTransformation(),
                        label = {
                            Text(
                                text = stringResource(
                                    R.string.keyword
                                ),
                                fontSize = 20.sp,
                                fontFamily = poppinsFamily,
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 25.dp)
                            .zIndex(1f)
                    )
                    Button(
                        onClick = {
                            val body = UserRegister(
                                nome = nameState.value,
                                email = emailState.value,
                                senha = passwordState.value,
                                palavraChave = keywordState.value
                            )

                            val sendUser = RetrofitFactory()
                                .getUserRegisterService()
                                .insertUser(body)

                            sendUser.enqueue(object : Callback<ResponsePost> {
                                override fun onResponse(
                                    call: Call<ResponsePost>,
                                    response: Response<ResponsePost>
                                ) {
                                    navegacao?.navigate("login")
                                    if (response.isSuccessful) {
                                        Toast.makeText(
                                            context,
                                            "Cadastro OK: ${response.body()?.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
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
                        },
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(top = 60.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFDF87))
                    ){
                        Text(
                            text = stringResource(R.string.register),
                            fontSize = 30.sp,
                            fontFamily = poppinsFamily,
                            color = Color(0xFF241508)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(end = 10.dp, top = 3.dp)
                            .fillMaxWidth()
                            .height(30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ){
                        Text(
                            text = stringResource(R.string.have_login),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = poppinsFamily,
                            color = Color.White,
                        )
                        Text(
                            text = stringResource(R.string.login),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFDF87),
                            modifier = Modifier
                                .clickable { navegacao?.navigate("login") }
                                .padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CadastroPessoaPreview(){
    CadastroPessoa(null)
}
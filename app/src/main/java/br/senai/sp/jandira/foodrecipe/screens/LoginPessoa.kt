package br.senai.sp.jandira.foodrecipe.screens

import android.content.Context
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
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.foodrecipe.R
import br.senai.sp.jandira.foodrecipe.model.ResponsePost
import br.senai.sp.jandira.foodrecipe.model.UserLogin
import br.senai.sp.jandira.foodrecipe.model.UserRegister
import br.senai.sp.jandira.foodrecipe.screens.components.DialogWelcome
import br.senai.sp.jandira.foodrecipe.service.RetrofitFactory
import br.senai.sp.jandira.foodrecipe.ui.theme.antonscFamily
import br.senai.sp.jandira.foodrecipe.ui.theme.poppinsFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginPessoa(
    navegacao: NavHostController?
){

    val senhaVisivel = remember { mutableStateOf(false) }

    val openAlertDialog = remember { mutableStateOf(false) }

    var emailState = remember {
        mutableStateOf("")
    }
    var passwordState = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val userFile = context.getSharedPreferences("user_file", Context.MODE_PRIVATE)
    val editor = userFile.edit()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painterResource(R.drawable.background2),
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
                    Image(
                        painter = painterResource(R.drawable.logowhite),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                            .size(160.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp)
                    ){
                        Text(
                            text = stringResource(R.string.login),
                            fontSize = 33.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = poppinsFamily,
                            color = Color.White
                        )
                        Text(
                            text = stringResource(R.string.message_receipe),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = antonscFamily,
                            color = Color(0xFFFFDF87)
                        )
                    }
                    OutlinedTextField(
                        value = emailState.value,
                        onValueChange = {emailState.value = it},
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
                                    .size(30.dp)
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
                            .padding(top = 70.dp)
                    )
                    OutlinedTextField(
                        value = passwordState.value,
                        onValueChange = { passwordState.value = it },
                        textStyle = TextStyle(color = Color.White), // força cor branca
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFF715F3E),
                            focusedContainerColor = Color(0xFF715F3E),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(60.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(30.dp)
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
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 25.dp)
                    )
                    Text(
                        text = stringResource(R.string.forgot_password),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppinsFamily,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 120.dp, top = 10.dp),
                    )
                    Button(
                        onClick = {
                            val body = UserLogin(
                                email = emailState.value,
                                senha = passwordState.value
                            )
//
                            val loginUser = RetrofitFactory()
                                .getUserRegisterService()
                                .loginUser(body)

                            loginUser.enqueue(object : Callback<ResponsePost>{
                                override fun onResponse(
                                    call: Call<ResponsePost>,
                                    response: Response<ResponsePost>
                                ) {
                                    openAlertDialog.value = true
//                                    if (response.isSuccessful) {
//                                        val userId = response.body()?.id_usuario
//                                        if (userId != null) {
//                                            editor.putInt("user_id", userId)
//                                            editor.apply() // Use apply() for async saving
//                                            Toast.makeText(context, "User ID stored: $userId", Toast.LENGTH_SHORT).show()
//                                        } else {
//                                            Toast.makeText(context, "User ID not found in response", Toast.LENGTH_SHORT).show()
//                                        }
//                                        openAlertDialog.value = true
//                                    } else {
//                                        Toast.makeText(context, "Login failed: ${response.code()}", Toast.LENGTH_SHORT).show()
//                                        Log.e("LoginPessoa", "Login failed: ${response.errorBody()?.string()}")
//                                    }
                                }
                                override fun onFailure(p0: Call<ResponsePost>, p1: Throwable) {
                                    Log.d("Erro" , "Erro")
                                }

                            })
                        },
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(top = 80.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFDF87))
                    ){
                        Text(
                            text = stringResource(R.string.login),
                            fontSize = 30.sp,
                            fontFamily = poppinsFamily,
                            color = Color(0xFF241508)
                        )
                    }
                    if (openAlertDialog.value) {
                        DialogWelcome(
                            onDismissRequest = { openAlertDialog.value = false },
                            onNavigate = {navegacao?.navigate("home1")},
                            dialogTitle = "BEM VINDO!",
                            dialogText = "Este é o lugar ideal para aprender novas comidas, explorar sabores e descobrir dicas práticas para se destacar na cozinha.",
                            dialogTextSpecial = "Aproveite e inspire-se!",
                            icon = Icons.Default.ArrowCircleRight
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 3.dp)
                            .fillMaxWidth()
                            .height(30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = stringResource(R.string.no_account),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = poppinsFamily,
                            color = Color.White,
                        )
                        Text(
                            text = stringResource(R.string.register),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFDF87),
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .clickable { navegacao?.navigate("cadastro") }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginPessoaPreview(){
    LoginPessoa(null)
}
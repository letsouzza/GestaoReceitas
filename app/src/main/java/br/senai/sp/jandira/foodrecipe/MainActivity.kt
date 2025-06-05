package br.senai.sp.jandira.foodrecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.foodrecipe.screens.CadastroIngrediente
import br.senai.sp.jandira.foodrecipe.screens.CadastroModoPreparo
import br.senai.sp.jandira.foodrecipe.screens.CadastroPessoa
import br.senai.sp.jandira.foodrecipe.screens.CadastroReceita
import br.senai.sp.jandira.foodrecipe.screens.LoginPessoa
import br.senai.sp.jandira.homereceita.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navegacao = rememberNavController()
            NavHost(
                navController = navegacao,
                startDestination = "login"
            ){
                composable(route = "login"){ LoginPessoa(navegacao)}
                composable(route = "cadastro"){ CadastroPessoa(navegacao)}
                composable(route = "home"){ HomeScreen(navegacao) }
                composable(route = "cadastroReceita"){ CadastroReceita(navegacao)}
                composable(route = "cadastroIngrediente"){ CadastroIngrediente(navegacao)}
                composable(route = "cadastroFinal"){ CadastroModoPreparo(navegacao) }
            }
        }
    }
}

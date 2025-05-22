package br.senai.sp.jandira.foodrecipe.model

data class UserRegister(
    var id: Int = 0,
    var nome: String = "",
    var email: String = "",
    var senha: String = "",
    var palavraChave: String = "",
    var fotoPerfil: String = ""
)

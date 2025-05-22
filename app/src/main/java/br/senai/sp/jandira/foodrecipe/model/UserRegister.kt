package br.senai.sp.jandira.foodrecipe.model

import com.google.gson.annotations.SerializedName

data class UserRegister(
    var id: Int = 0,
    var nome: String = "",
    var email: String = "",
    var senha: String = "",
    @SerializedName("palavra_chave") var palavraChave: String = "",
    @SerializedName("foto_perfil") var fotoPerfil: String = ""
)

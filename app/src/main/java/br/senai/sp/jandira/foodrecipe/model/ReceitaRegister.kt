package br.senai.sp.jandira.foodrecipe.model

import com.google.gson.annotations.SerializedName

//data class CategoriaId(
//    @SerializedName("id") val id: Int
//)

data class ReceitaRegister(
    var id: Int = 0,
    var titulo: String = "",
    var descricao: String = "",
    @SerializedName("modo_de_preparo") var modoPreparo: String = "",
    @SerializedName("imagem_receita") var imagem: String = "",
    var ingredientes: String = "",
    @SerializedName("tempo_preparo") var tempo: String = "",
    var porcoes: String = "",
    @SerializedName("id_usuarios") var idUser: Int = 1,
    @SerializedName("id_nivel_dificuldade") var nivelDificuldade: Int = 0,
    val categoria: List<Categoria> // ou List<Int> dependendo do tipo
)

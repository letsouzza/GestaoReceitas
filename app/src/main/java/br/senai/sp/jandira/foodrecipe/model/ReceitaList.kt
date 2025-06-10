package br.senai.sp.jandira.foodrecipe.model

import com.google.gson.annotations.SerializedName

data class ReceitaList(
    var id: Int = 0,
    var titulo: String = "",
    var descricao: String = "",
    @SerializedName("imagem_receita") var imagem: String = "",
    @SerializedName("tempo_preparo") var tempo: String = ""
)

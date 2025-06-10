package br.senai.sp.jandira.foodrecipe.model

import com.google.gson.annotations.SerializedName

data class ResultReceita(
    @SerializedName ("receitas") var receitas: List<ReceitaList>
)

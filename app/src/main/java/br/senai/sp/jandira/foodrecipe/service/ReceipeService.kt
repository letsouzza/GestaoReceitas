package br.senai.sp.jandira.foodrecipe.service

import br.senai.sp.jandira.foodrecipe.model.ResultCategoria
import br.senai.sp.jandira.foodrecipe.model.ResultLevel
import retrofit2.http.GET

interface ReceipeService {

    @GET("nivelDificuldade")
    fun listLevel(): retrofit2.Call<ResultLevel>

    @GET("categoria")
    fun listCategoria(): retrofit2.Call<ResultCategoria>
}
package br.senai.sp.jandira.foodrecipe.service

import android.telecom.Call
import br.senai.sp.jandira.foodrecipe.model.ReceitaRegister
import br.senai.sp.jandira.foodrecipe.model.ResponsePost
import br.senai.sp.jandira.foodrecipe.model.ResultCategoria
import br.senai.sp.jandira.foodrecipe.model.ResultLevel
import br.senai.sp.jandira.foodrecipe.model.ResultReceita
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ReceipeService {

    @GET("nivelDificuldade")
    fun listLevel(): retrofit2.Call<ResultLevel>

    @GET("categoria")
    fun listCategoria(): retrofit2.Call<ResultCategoria>

    @GET("receita")
    fun mostrarReceita(): retrofit2.Call<ResultReceita>

    @Headers("Content-Type: application/json")
    @POST("receita")
    fun insertReceita(@Body receita: ReceitaRegister): retrofit2.Call<ResponsePost>
}
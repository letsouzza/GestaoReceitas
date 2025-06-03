package br.senai.sp.jandira.foodrecipe.service

import android.telecom.Call
import retrofit2.http.GET

interface ReceipeService {

    @GET("nivelDificuldade")
    fun listLevel(): Call<ResultLevel>
}
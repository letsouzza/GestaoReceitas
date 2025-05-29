package br.senai.sp.jandira.foodrecipe.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "http://10.107.144.11:8080/v1/controle-receitas/"

    private val RETROFIT_FACTORY = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getUserRegisterService() : UserService{
        return RETROFIT_FACTORY
            .create(UserService::class.java)
    }
}
package br.senai.sp.jandira.foodrecipe.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    // IP Mac- 10.107.144.11


    private val BASE_URL = "http://10.107.134.9:8080/v1/controle-receitas/"

    private val RETROFIT_FACTORY = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getUserRegisterService() : UserService{
        return RETROFIT_FACTORY
            .create(UserService::class.java)
    }

    fun getLevelService() : ReceipeService{
        return RETROFIT_FACTORY
            .create(ReceipeService::class.java)
        // Criar o serviço
    }

    fun getCategoryService() : ReceipeService{
        return RETROFIT_FACTORY
            .create(ReceipeService::class.java)
        // Criar o serviço
    }

    fun getReceipeService() : ReceipeService{
        return RETROFIT_FACTORY
            .create(ReceipeService::class.java)
        // Criar o serviço
    }
}
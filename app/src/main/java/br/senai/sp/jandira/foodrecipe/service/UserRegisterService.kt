package br.senai.sp.jandira.foodrecipe.service

import br.senai.sp.jandira.foodrecipe.model.ResponsePost
import br.senai.sp.jandira.foodrecipe.model.UserRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegisterService {

    @POST("usuario")
    fun insertUser(@Body user: UserRegister): Call<ResponsePost>

}
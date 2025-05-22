package br.senai.sp.jandira.foodrecipe.model

data class ResponsePost(
    var status: Boolean = false,
    var statusCode: Int = 0,
    var message: String = ""
)

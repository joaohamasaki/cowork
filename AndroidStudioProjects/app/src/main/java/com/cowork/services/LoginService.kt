package com.cowork.services

import com.cowork.model.Token
import com.cowork.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("/api/login")
    fun fazerLogin(@Body user: User): Call<Token>
}
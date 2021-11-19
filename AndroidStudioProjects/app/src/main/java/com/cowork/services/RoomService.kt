package com.cowork.services

import com.cowork.model.Room
import retrofit2.Call
import retrofit2.http.*

interface RoomService {

    @GET("api/room")
    fun listar(): Call<List<Room>>

    @GET("api/room/{name}")
    fun pesquisar(@Path("name") name:String): Call<List<Room>>


}
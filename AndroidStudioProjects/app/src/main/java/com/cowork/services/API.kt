package com.cowork.services


import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val ARQUIVO_LOGIN = "login"

class API(private val context: Context) {
    private val baseURL = "http://10.0.2.2:8000"
    private val timeout = 30L

    private val retrofit: Retrofit

    get(){
        val okHttp = OkHttpClient.Builder()
            .readTimeout(timeout, TimeUnit.SECONDS)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()
    }

    private val retrofitSeguro: Retrofit
        get(){
            val autenticador = AutenticadorToken(context)
            val okHttp = OkHttpClient.Builder()
                .readTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .addInterceptor(autenticador)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build()

        }

    val login: LoginService
        get(){
            return retrofit.create(LoginService::class.java)
        }

    val room: RoomService
        get(){
            return retrofitSeguro.create(RoomService::class.java)
        }

}
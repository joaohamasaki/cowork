package com.cowork.services

import android.content.Context
import com.cowork.model.Token
import com.cowork.model.User
import okhttp3.*

class AutenticadorToken(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       // val prefs = context.getSharedPreferences("",Context.MODE_PRIVATE)
       // val token = prefs.getString("token", "") as String
        var request = chain.request()
        request = request?.newBuilder()?.addHeader("Authorization", "Bearer 2|TNIixr8ir66Np8wtoVSt69iqW8neOyYCZk6MRPXY")?.build()

        return chain.proceed(request)

    }

    /*override fun authenticate(route: Route?, response: Response): Request? {
        val prefs = context.getSharedPreferences(ARQUIVO_LOGIN, Context.MODE_PRIVATE)
        val usuario = prefs.getString("usuario","") as String
        val senha = prefs.getString("senha","") as String

        val user = User(email = usuario, password = senha)

        val respostaRetrofit = API(context).login.fazerLogin(user).execute()

        var token = respostaRetrofit.body()
        if(respostaRetrofit.isSuccessful && token != null){
            var editor = prefs.edit()
            editor.putString("token", token.token)
            editor.apply()

            return response?.request()?.newBuilder()?.header("token", token.token)?.build()
        }
        return null
    }*/

}
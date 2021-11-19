package com.cowork.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cowork.databinding.ActivityMainBinding
import com.cowork.model.Token
import com.cowork.model.User
import com.cowork.services.API
import com.cowork.services.ARQUIVO_LOGIN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEntrar.setOnClickListener{
            val usuario = binding.editEmailAddress.text.toString()
            val senha = binding.editPassword.text.toString()

            val callback = object: Callback<Token>{
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    val token = response.body()

                    if(response.isSuccessful && token != null){
                        val editor = getSharedPreferences(ARQUIVO_LOGIN, Context.MODE_PRIVATE).edit()
                        editor.putString("usuario", usuario)
                        editor.putString("senha", senha)
                        editor.putString("token", token.token)
                        editor.apply()

                        Toast.makeText(this@MainActivity, "Login Efetuado", Toast.LENGTH_LONG).show()
                    }
                    else{
                        var msg = response.message().toString()
                        if(msg == ""){
                            msg = "Não foi possivel efetuar seu login"
                        }
                        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                        response.errorBody()?.let{
                            Log.e("MainActivity", it.string())
                        }
                    }

                }

                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "", Toast.LENGTH_LONG).show()
                    Log.e("MainActivity", "OnCreate", t)
                }

            }

            val user = User(email = usuario, password = senha)

            API(this).login.fazerLogin(user).enqueue(callback)
        }

    }
}
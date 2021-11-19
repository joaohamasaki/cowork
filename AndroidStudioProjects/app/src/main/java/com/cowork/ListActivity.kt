package com.cowork


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cowork.databinding.ActivityListBinding
import com.cowork.databinding.CardItemBinding
import com.cowork.model.Room
import com.cowork.services.API
import com.cowork.services.RoomService
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        atualizarRooms()
    }
    fun atualizarRooms(){

        val callback = object : Callback<List<Room>> {
            override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {

                if (response.isSuccessful){
                    val listaRooms = response.body()
                    atualizarUI(listaRooms)
                }
                else{
                    //val error = response.errorBody().toString()
                    Snackbar.make(binding.container, "Não é possível atualizar os espaços",
                        Snackbar.LENGTH_LONG).show()

                    Log.e("ERROR", response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<List<Room>>, t: Throwable) {
                Snackbar.make(binding.container, "Não é possível se conectar ao servidor",
                    Snackbar.LENGTH_LONG).show()

                Log.e("ERROR", "Falha ao executar serviço", t)

            }
        }

        API(this).room.listar().enqueue(callback)
    }

    fun atualizarUI(lista: List<Room>?){
        binding.container.removeAllViews()
        lista?.forEach{
            val cardBinding = CardItemBinding.inflate(layoutInflater)

            cardBinding.texTitulo.text = it.name
            cardBinding.textDesc.text = it.description

            Picasso.get().load("http://10.0.2.2:8000/${it.image}").into(cardBinding.imagem)

            binding.container.addView(cardBinding.root)
        }

    }

}




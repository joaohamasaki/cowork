package com.cowork


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cowork.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    lateinit var binding:ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityCadastroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
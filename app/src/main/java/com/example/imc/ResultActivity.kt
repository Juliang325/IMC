package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imc.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {

    private lateinit var binding:ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mensajeRecibido = intent.getStringExtra("mensaje")

        val mensajeDouble = mensajeRecibido.toString().toDouble()

        if(mensajeDouble <= 18.5){
            binding.tvResult.text = "Su IMC es: $mensajeRecibido, tiene un peso inferior al normal"
        }
        else if (mensajeDouble <= 24.9){
            binding.tvResult.text = "Su IMC es: $mensajeRecibido, tiene un peso normal"
        }
        else if (mensajeDouble <= 29.9){
            binding.tvResult.text = "Su IMC es: $mensajeRecibido, tiene un peso un poco superior al normal"
        }
        else{
            binding.tvResult.text = "Su IMC es: $mensajeRecibido, tiene obesidad"
        }


    }
}
package com.example.sorrisoemjogo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sorrisoemjogo.databinding.ActivityDicasSaudeBucalBinding

class DicasSaudeBucal : AppCompatActivity() {

    private val binding by lazy{
        ActivityDicasSaudeBucalBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnHome.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        binding.btnFoto.setOnClickListener{
            val intent = Intent(this, RegistrarSorriso::class.java)
            startActivity(intent)
        }

    }
}
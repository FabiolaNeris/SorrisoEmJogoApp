package com.example.sorrisoemjogo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sorrisoemjogo.databinding.ActivityRegistrarSorrisoBinding

class RegistrarSorriso : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegistrarSorrisoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnHome2.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

    }
}
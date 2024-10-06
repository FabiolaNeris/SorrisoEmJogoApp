package com.example.sorrisoemjogo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {

    private lateinit var btnSair:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        btnSair = findViewById(R.id.imgSair)

        btnSair.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
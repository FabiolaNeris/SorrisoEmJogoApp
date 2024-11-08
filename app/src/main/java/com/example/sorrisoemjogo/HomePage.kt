package com.example.sorrisoemjogo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sorrisoemjogo.databinding.ActivityHomePageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.rpc.context.AttributeContext.Auth

class HomePage : AppCompatActivity() {


    private val binding by lazy{
        ActivityHomePageBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        binding.imgSair.setOnClickListener{

            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
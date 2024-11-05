package com.example.sorrisoemjogo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sorrisoemjogo.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            startActivity(Intent(this, HomePage::class.java))
            finish()
        }

        binding.imgLogin.setOnClickListener{
            val email = binding.textEmail.text.toString()
            val senha = binding.textSenha.text.toString()

            if(email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this, HomePage::class.java))
                        finish()
                    }else{
                        if(task.exception is FirebaseAuthInvalidUserException){
                            val intent = Intent(this, TelaCadastro::class.java)
                            intent.putExtra("email", email)
                            intent.putExtra("senha", senha)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Erro no login: ${task.exception?.message}",
                            Toast.LENGTH_SHORT).show()
                        }
                    }
                }

        }

    }




}


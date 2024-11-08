package com.example.sorrisoemjogo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sorrisoemjogo.databinding.ActivityMainBinding
import com.example.sorrisoemjogo.databinding.ActivityTelaCadastroBinding
import com.google.firebase.auth.FirebaseAuth

class TelaCadastro : AppCompatActivity() {
    private val binding by lazy{
        ActivityTelaCadastroBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        intent.getStringExtra("email")?.let{
            binding.digiteEmail.setText(it)
        }
        intent.getStringExtra("senha")?.let {
            binding.digiteSenha.setText(it)
        }

        binding.btnCadastrar.setOnClickListener{
            val email = binding.digiteEmail.text.toString()
            val senha = binding.digiteSenha.text.toString()

            if(email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Cadastro realizado com sucesso",
                            Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this, "Erro: ${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                    }
                }
        }


        binding.btnVoltar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
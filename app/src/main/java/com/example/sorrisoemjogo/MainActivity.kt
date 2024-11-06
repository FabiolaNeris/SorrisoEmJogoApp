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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
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

            if(!isValidEmail(email)){
                Toast.makeText(this, "Email invalido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this, HomePage::class.java))
                        finish()
                    }else{
                        when(task.exception){
                            is FirebaseAuthInvalidCredentialsException ->{
                                val intent = Intent(this, TelaCadastro::class.java)
                                intent.putExtra("email", email)
                                intent.putExtra("senha", senha)
                                startActivity(intent)
                            }
                            is FirebaseAuthInvalidUserException  ->{
                                Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show()
                            }
                            else ->{
                                Toast.makeText(
                                    this,
                                    "Erro no login: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

        }

        binding.textVEsqueceuSenha.setOnClickListener{
            val email = binding.textEmail.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this, "Digite seu email para recuperar a senha",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Email de recuperação enviado",
                        Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Erro ao enviar email de recuperação: ${task.exception?.message}",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.textVNaoTemCadastro.setOnClickListener{
            startActivity(Intent(this, TelaCadastro::class.java))
        }
    }

    private fun isValidEmail(email: String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}


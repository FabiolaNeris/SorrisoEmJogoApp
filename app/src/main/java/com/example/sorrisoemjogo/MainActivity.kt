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

class MainActivity : AppCompatActivity() {

    private lateinit var userEmail: EditText
    private lateinit var userPassword : EditText
    private lateinit var loginButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        userEmail = findViewById(R.id.textEmail)
        userPassword = findViewById(R.id.textSenha)
        loginButton = findViewById(R.id.imgLogin)


        loginButton.setOnClickListener{
            val user = userEmail.text.toString()
            val senha = userPassword.text.toString()


            if(CredenciaisValidas (user, senha)){
                Toast.makeText(this, "Login Efetuado", Toast.LENGTH_SHORT).show()

               val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Senha Incorreta", Toast.LENGTH_SHORT).show()
            }

        }

    }


    private fun CredenciaisValidas(user:String, senha: String):Boolean{
        return user == "fabiola" && senha == "123456"
    }
}


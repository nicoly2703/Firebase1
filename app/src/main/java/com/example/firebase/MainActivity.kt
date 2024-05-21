package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    val COD_TELA = 2
    private val autentication by lazy {
        FirebaseAuth.getInstance()
    }
    data class UserModelo(val email: String, val senha: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btlogin = findViewById<Button>(R.id.btnLogin)
        val btcadastro = findViewById<Button>(R.id.btnCadastro)
        val user = autentication.currentUser
        if (user != null) {
            abrirLogin()
        }

        btlogin.setOnClickListener {
            var email = findViewById<EditText>(R.id.edtEmail)
            var senha = findViewById<EditText>(R.id.edtSenha)
            var txtEmail = email?.text.toString()
            var txtSenha = senha?.text.toString()


            if (txtEmail.isNullOrEmpty() && txtSenha.isNullOrEmpty()) {
                Toast.makeText(this, "Preencher todos os campos", Toast.LENGTH_SHORT).show()

            } else {
                btlogin_Click()
            }

        }
        btcadastro.setOnClickListener {
            var email = findViewById<EditText>(R.id.edtEmail)
            var senha = findViewById<EditText>(R.id.edtSenha)
            var txtEmail = email?.text.toString()
            var txtSenha = senha?.text.toString()

            if (txtEmail.isNullOrEmpty() && txtSenha.isNullOrEmpty()) {
                Toast.makeText(this, "Preencher todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                btcadastro_Click()
            }
        }
    }

    private fun btlogin_Click() {
        val etEmail = findViewById<EditText>(R.id.edtEmail)
        val etSenha = findViewById<EditText>(R.id.edtSenha)
        var txtEmail = etEmail.text.toString()
        var txtSenha = etSenha.text.toString()
        val usuario = UserModelo(txtEmail,txtSenha)
        autentication.signInWithEmailAndPassword(
            usuario.email, usuario.senha).addOnCanceledListener {
            abrirLogin()
        }.addOnFailureListener {
            Toast.makeText(this, "erro ao efetuar o login", Toast.LENGTH_LONG).show()
        }
    }

    fun btcadastro_Click() {
        val etEmail = findViewById<EditText>(R.id.edtEmail)
        val etSenha = findViewById<EditText>(R.id.edtSenha)
        var txtEmail = etEmail?.text.toString()
        var txtSenha = etSenha?.text.toString()
        val usuario = UserModelo(txtEmail,txtSenha)
        autentication.createUserWithEmailAndPassword(

            usuario.email, usuario.senha
        ).addOnSuccessListener {
            Toast.makeText(this, "cadastrado com sucesso!", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "erro ao efetuar o cadastro", Toast.LENGTH_LONG).show()
        }
    }

    fun abrirLogin() {
        var email = findViewById<EditText>(R.id.edtEmail)
        var senha = findViewById<EditText>(R.id.edtSenha)
        var txtEmail = email?.text.toString()
        var txtSenha = senha?.text.toString()
        val usuario = UserModelo(txtEmail, txtSenha)
        val intent = Intent(this, inicio::class.java).apply {
            putExtra("senha", usuario.senha)
            putExtra("email", usuario.email)
        }
        startActivityForResult(intent, COD_TELA)
        senha.setText("")
        email.setText("")
    }
}



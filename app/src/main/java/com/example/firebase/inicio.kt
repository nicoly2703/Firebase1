package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class inicio : AppCompatActivity() {
        private val autentication by lazy {
            FirebaseAuth.getInstance()
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_inicio)
            val email = findViewById<TextView>(R.id.idmail)
            // val senha = findViewById<TextView>(R.id.idsenha)
            val txtemail = intent.getStringExtra("email")
            email.setText(txtemail)
            val txtsenha = intent.getStringExtra("senha")
            //senha.setText(txtsenha)
            val btnsair = findViewById<Button>(R.id.btnlogin)
            btnsair.setOnClickListener {
                logout()
            }
        }

        fun logout() {
            autentication.signOut()
            finish()
        }

}
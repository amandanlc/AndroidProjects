package com.example.jrwallet

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var irParaLogin : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        irParaLogin = findViewById(R.id.btnIrParaLogin)

        irParaLogin.setOnClickListener { chamatela() }
    }

    fun chamatela(){
        startActivity(Intent(this, Login::class.java))
    }
}
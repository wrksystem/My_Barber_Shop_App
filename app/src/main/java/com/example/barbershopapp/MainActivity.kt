package com.example.barbershopapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.barbershopapp.databinding.ActivityMainBinding
import com.example.barbershopapp.view.Home
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btLogin.setOnClickListener {

            val name = binding.editNome.text.toString()
            val password = binding.editPassword.text.toString()

            when{
                 name.isEmpty() -> {
                     message(it, "Coloque o seu nome!")
                 }password.isEmpty() -> {
                     message(it, "Preencha a senha!")
                 }password.length <=5 -> {
                     message(it, "A senha precisa ter pelo menos 6 caracteres !")
                 }else -> {
                     navigationForHome(name)
                 }
            }
        }
    }

    private fun message(view: View, message: String){

        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    private fun navigationForHome (name: String) {
        val intent = Intent(this, Home::class.java)
        intent.putExtra("nome", name)
        startActivity(intent)
    }

}
package com.example.barbershopapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.barbershopapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}
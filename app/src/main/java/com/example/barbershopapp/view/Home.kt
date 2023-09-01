package com.example.barbershopapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.barbershopapp.R
import com.example.barbershopapp.adapter.ServicesAdapter
import com.example.barbershopapp.databinding.ActivityHomeBinding
import com.example.barbershopapp.model.Services

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicesAdapter: ServicesAdapter
    private val listServices: MutableList<Services> = mutableListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val name = intent.extras?.getString("nome")

        binding.txtNameUser.text = "Bem-vindo(a), $name!"
        val recyclerViewServices = binding.recyclerViewServices
        recyclerViewServices.layoutManager = GridLayoutManager(this, 2)
        servicesAdapter = ServicesAdapter(this, listServices)
        recyclerViewServices.setHasFixedSize(true)
        recyclerViewServices.adapter = servicesAdapter
        getSetvices()

        binding.btToSchedule.setOnClickListener {
            val intent = Intent(this, Schedule::class.java)
            intent.putExtra("nome", name)
            startActivity(intent)
        }
    }

    private fun getSetvices() {

        val serviceOne = Services(R.drawable.img1, "Corte de Cabelo")
        listServices.add(serviceOne)

        val serviceTwo = Services(R.drawable.img2, "Corte de Barba")
        listServices.add(serviceTwo)

        val serviceThree = Services(R.drawable.img3, "Lavagem de Cabelo")
        listServices.add(serviceThree)

        val serviceFour = Services(R.drawable.img4, "Tratamento de Cabelo")
        listServices.add(serviceFour)
    }

}
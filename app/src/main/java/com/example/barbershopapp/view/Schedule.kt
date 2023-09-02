package com.example.barbershopapp.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.barbershopapp.databinding.ActivityScheduleBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class Schedule : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var date: String = ""
    private var hour: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val name = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener{_, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            var day = dayOfMonth.toString()
            val month: String

            if (dayOfMonth < 10) {
                day = "0$dayOfMonth"
            }
            if (monthOfYear < 10) {
                month = "0${monthOfYear + 1}"
            } else {
                month = (monthOfYear + 1).toString()
            }
            date = "$day/$month/$year"
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            val min: String

            if (minute < 10) {
                min = "0$minute"
            }else{
                min = minute.toString()
            }

            hour = "$hourOfDay:$min"
        }
        binding.timePicker.setIs24HourView(true) //formato da hora
        binding.btSchedule.setOnClickListener {
            val barberOne = binding.barberOne
            val barberTwo = binding.barberTwo
            val barberThree = binding.barberThree

            when{
                hour.isEmpty() -> {
                    message(it, "Preencha a horário!", "#FF0000")
                }
                hour < "08:00" && hour > "19:00" -> {
                    message(it, "Barber Shop esta fechado - Horário de atendimento: 08:00 às 19:00", "#FF0000")
                }date.isEmpty() -> {
                    message(it, "Preencha a data!", "#FF0000")
                }
                barberOne.isChecked && date.isNotEmpty() && hour.isNotEmpty() -> {
                    saveSchedule(it,name,"Pedro da Silva",date,hour)
                }
                barberTwo.isChecked && date.isNotEmpty() && hour.isNotEmpty() -> {
                    saveSchedule(it,name,"Guilherme Henrique",date,hour)
                }
                barberThree.isChecked && date.isNotEmpty() && hour.isNotEmpty() -> {
                    saveSchedule(it,name,"João Marcos",date,hour)
                }
                else -> {
                    message(it, "Escolha um barbeiro!", "#FF0000")
                }
            }
        }
    }

    private fun message(view: View, message: String, color: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(Color.parseColor(color))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    private fun saveSchedule(view: View, client: String,  barber: String, date: String, hour: String) {
        val db = FirebaseFirestore.getInstance()

        val dataUser = hashMapOf(
            "cliente" to client,
            "barbeiro" to barber,
            "data" to date,
            "hora" to hour
        )

        db.collection("schedule").document(client).set(dataUser).addOnCompleteListener{
            message(view, "Agendamento realizado com sucesso!", "#FF03DAC5")
        }.addOnFailureListener{
            message(view, "Erro ao realizar o agendamento!", "#FF0000")
        }

    }
}
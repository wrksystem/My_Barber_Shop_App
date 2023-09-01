package com.example.barbershopapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barbershopapp.databinding.ServicesItemBinding
import com.example.barbershopapp.model.Services

class ServicesAdapter(private val context: Context, private val listServices: MutableList<Services>) :
    RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val itemList = ServicesItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ServicesViewHolder(itemList)
    }
    override fun getItemCount() = listServices.size

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.imgServices.setImageResource(listServices[position].img!!)
        holder.txtServices.text = listServices[position].name
    }

    inner class ServicesViewHolder(binding: ServicesItemBinding): RecyclerView.ViewHolder(binding.root){
        val imgServices = binding.imgServices
        val txtServices = binding.txtServices
    }

}
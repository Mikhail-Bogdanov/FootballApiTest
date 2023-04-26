package com.example.apitest.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apitest.databinding.RecyclerViewMatchesItemBinding
import com.example.data.footballModel.Data


class RecyclerViewMatchesAdapter (
    var data: List<Data>,
    val clickListener: (pos: Int) -> Int
) : RecyclerView.Adapter<RecyclerViewMatchesAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: RecyclerViewMatchesItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = RecyclerViewMatchesItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = with(holder) {
        with(binding){
            tvHomeTeam.text = data[position].home_team.full_name
            tvVisitorTeam.text = data[position].visitor_team.full_name
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<Data>){
        this.data = data
        notifyDataSetChanged()
    }
}
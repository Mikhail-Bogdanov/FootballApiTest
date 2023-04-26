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
            with(data[position]) {
                tvNameHomeTeam.text = home_team.full_name
                tvNameVisitorTeam.text = visitor_team.full_name

                tvCityHomeTeam.text = home_team.city
                tvCityVisitorTeam.text = visitor_team.city

                val homeTeamScore = home_team_score
                val visitorTeamScore = visitor_team_score

                tvScoreHomeTeam.text = homeTeamScore.toString()
                tvScoreVisitorTeam.text = visitorTeamScore.toString()

                if(homeTeamScore > visitorTeamScore) {
                    tvResultHomeTeam.text = "Winner"

                } else {
                    tvResultHomeTeam.text = "Looser"
                }

                if(homeTeamScore < visitorTeamScore) {
                    tvResultVisitorTeam.text = "Winner"
                } else {
                    tvResultVisitorTeam.text = "Looser"
                }

                tvFinal.text = if(postseason) "Final" else "Match"

                tvDate.text = date.slice(0..9) //потому что дата приходит с сервера кривая

                tvSeason.text = season.toString()
            }

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
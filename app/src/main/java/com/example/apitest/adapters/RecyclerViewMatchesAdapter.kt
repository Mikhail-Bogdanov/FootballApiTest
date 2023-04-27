package com.example.apitest.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apitest.databinding.RecyclerViewMatchesItemBinding
import com.example.data.database.model.DataModel

class RecyclerViewMatchesAdapter (
    var data: List<DataModel>
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
                tvNameHomeTeam.text = home_team.full_nameH
                tvNameVisitorTeam.text = visitor_team.full_nameV

                tvCityHomeTeam.text = home_team.cityH
                tvCityVisitorTeam.text = visitor_team.cityV

                val homeTeamScore = home_team_score
                val visitorTeamScore = visitor_team_score

                tvScoreHomeTeam.text = homeTeamScore.toString()
                tvScoreVisitorTeam.text = visitorTeamScore.toString()

                if(homeTeamScore > visitorTeamScore) {
                    tvResultHomeTeam.text = "Winner"
                    tvResultHomeTeam.setTextColor(Color.GREEN)
                } else {
                    tvResultHomeTeam.text = "Looser"
                    tvResultHomeTeam.setTextColor(Color.RED)
                }

                if(homeTeamScore < visitorTeamScore) {
                    tvResultVisitorTeam.text = "Winner"
                    tvResultVisitorTeam.setTextColor(Color.GREEN)
                } else {
                    tvResultVisitorTeam.text = "Looser"
                    tvResultVisitorTeam.setTextColor(Color.RED)
                }

                tvFinal.text = if(postseason) "Final" else "Match"
                tvFinal.setTextColor(Color.BLUE)

                tvDate.text = date.slice(0..9) //потому что дата приходит с сервера кривая

                tvSeason.text = season.toString()
            }

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<DataModel>){
        this.data = data
        notifyDataSetChanged()
    }
}
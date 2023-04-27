package com.example.data.utility

import com.example.data.database.model.DataModel
import com.example.data.database.model.HomeTeamModel
import com.example.data.database.model.VisitorTeamModel
import com.example.data.footballModel.Data

object Mapper {

    fun mapDataToDataModel(data: Data): DataModel = with(data) {

        val homeTeamModel= HomeTeamModel(
            abbreviationH = home_team.abbreviation,
            cityH = home_team.city,
            conferenceH = home_team.conference,
            divisionH = home_team.division,
            full_nameH = home_team.full_name,
            idH = home_team.id,
            nameH = home_team.name
        )

        val visitorTeamModel= VisitorTeamModel(
            abbreviationV = visitor_team.abbreviation,
            cityV = visitor_team.city,
            conferenceV = visitor_team.conference,
            divisionV = visitor_team.division,
            full_nameV = visitor_team.full_name,
            idV = visitor_team.id,
            nameV = visitor_team.name
        )

        return DataModel(
            date = date,
            home_team_score = home_team_score,
            id = id,
            period = period,
            postseason = postseason,
            season = season,
            status = status,
            time = time,
            visitor_team_score = visitor_team_score,
            home_team = homeTeamModel,
            visitor_team = visitorTeamModel
        )
    }
}
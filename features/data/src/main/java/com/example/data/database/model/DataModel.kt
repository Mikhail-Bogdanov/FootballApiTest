package com.example.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mainTable")
data class DataModel(
    val date: String,
    @Embedded
    val home_team: HomeTeamModel,
    val home_team_score: Int,
    @PrimaryKey
    val id: Int,
    val period: Int,
    val postseason: Boolean,
    val season: Int,
    val status: String,
    val time: String,
    @Embedded
    val visitor_team: VisitorTeamModel,
    val visitor_team_score: Int
)
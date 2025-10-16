package com.finder.roomrelation.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exam(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val resultRank: Int,
    val birthdayMonth:String
)

package com.finder.roomrelation.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//referencing table
@Entity
data class UserDetailsEntity(
    @PrimaryKey(autoGenerate = true) val userDetailsId: Long = 0, // Primary key field
    val address: String,
    val age: Int,
    val phoneNumber: String,
    val userId: Long //fk as referenced table
)

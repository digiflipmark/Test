package com.finder.roomrelation.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithDetailModel(

    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val userDetails: List<UserDetailsEntity>


)

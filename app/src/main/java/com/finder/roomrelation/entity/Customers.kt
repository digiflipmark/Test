package com.finder.roomrelation.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customers(
    @PrimaryKey(autoGenerate = true) val customerId: Long = 0,
    val description: String,
    val productCategory: String,
    val quantity: Int,
    val unitPrice: Double,
    val transactionsId: Int
)

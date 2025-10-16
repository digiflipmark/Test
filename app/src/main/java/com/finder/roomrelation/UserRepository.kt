package com.finder.roomrelation

import com.finder.roomrelation.entity.Customers
import com.finder.roomrelation.entity.Exam
import com.finder.roomrelation.entity.Orders
import com.finder.roomrelation.entity.UserDetailsEntity
import com.finder.roomrelation.entity.UserEntity
import com.finder.roomrelation.entity.UserWithDetailModel

interface UserRepository {

    suspend fun insertUser(user: List<UserEntity>)
    suspend fun insertUserDetails(user: List<UserDetailsEntity>)
    fun getUserWithDetails(userId: Long): List<UserWithDetailModel>

    // Group By Customers
    suspend fun insertCustomers(customers: List<Customers>)
    fun getCustomers(): List<Customers>

    //order date time fin
    suspend fun insertOrder(order: List<Orders>)

    //exam data

    suspend fun insertExamRank(exam: List<Exam>)

}
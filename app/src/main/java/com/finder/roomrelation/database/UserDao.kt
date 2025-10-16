package com.finder.roomrelation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.finder.roomrelation.entity.Customers
import com.finder.roomrelation.entity.Exam
import com.finder.roomrelation.entity.Orders
import com.finder.roomrelation.entity.UserDetailsEntity
import com.finder.roomrelation.entity.UserEntity
import com.finder.roomrelation.entity.UserWithDetailModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: List<Orders>)

    @Insert
    suspend fun insertExamRank(exam: List<Exam>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetails(user: List<UserDetailsEntity>)

    // Add other queries and operations as needed

    @Query("SELECT * FROM UserEntity where userId = :userId")
    fun getUserWithDetails(userId: Long): List<UserWithDetailModel>


    // Group By Customers

    @Insert
    suspend fun insertCustomers(customers: List<Customers>)

    @Query("SELECT * FROM Customers")
    fun getCustomers(): List<Customers>
}
package com.finder.roomrelation

import com.finder.roomrelation.database.UserDao
import com.finder.roomrelation.entity.Customers
import com.finder.roomrelation.entity.Exam
import com.finder.roomrelation.entity.Orders
import com.finder.roomrelation.entity.UserDetailsEntity
import com.finder.roomrelation.entity.UserEntity
import com.finder.roomrelation.entity.UserWithDetailModel
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun insertUser(user: List<UserEntity>) {
        userDao.insertUser(user)
    }

    override suspend fun insertUserDetails(user: List<UserDetailsEntity>) {
        userDao.insertUserDetails(user)
    }

    override fun getUserWithDetails(userId: Long): List<UserWithDetailModel> {
        return userDao.getUserWithDetails(userId)
    }

    override suspend fun insertCustomers(customers: List<Customers>) {
        userDao.insertCustomers(customers)
    }

    override fun getCustomers(): List<Customers> {
        return userDao.getCustomers()
    }

    override suspend fun insertOrder(order: List<Orders>) {
        userDao.insertOrder(order)

    }

    override suspend fun insertExamRank(exam: List<Exam>) {
        userDao.insertExamRank(exam)
    }


}
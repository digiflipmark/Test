package com.finder.roomrelation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.finder.roomrelation.entity.Customers
import com.finder.roomrelation.entity.Exam
import com.finder.roomrelation.entity.Orders
import com.finder.roomrelation.entity.UserDetailsEntity
import com.finder.roomrelation.entity.UserEntity

@Database(
    entities = [UserEntity::class, UserDetailsEntity::class, Customers::class, Orders::class, Exam::class],
    version = 9,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "user_database"
    }


}
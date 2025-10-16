package com.finder.roomrelation.di

import android.content.Context
import androidx.room.Room
import com.finder.roomrelation.UserRepository
import com.finder.roomrelation.UserRepositoryImpl
import com.finder.roomrelation.database.UserDao
import com.finder.roomrelation.database.UserDatabase
import com.finder.roomrelation.database.UserDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            DATABASE_NAME// Database name
        ).fallbackToDestructiveMigration().
        build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase):UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao = userDao)
    }


}
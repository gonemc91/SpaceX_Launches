package com.example.pagging_remote_medaitor_spacex.di

import android.content.Context
import androidx.room.Room
import com.example.pagging_remote_medaitor_spacex.data.room.AppDatabase
import com.example.pagging_remote_medaitor_spacex.data.room.LaunchesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DatabaseModule {

    @Singleton
    @Provides
    fun providerDatabase(
        @ApplicationContext context: Context
    ): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        )
            .build()
    }


    @Provides
    fun provideLauncherDao (database: AppDatabase): LaunchesDao {
        return database.getLaunchesDao()
    }

    private companion object{
        const val DB_NAME = "launches.db"
}
}
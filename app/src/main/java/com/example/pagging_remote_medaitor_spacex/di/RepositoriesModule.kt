package com.example.pagging_remote_medaitor_spacex.di

import androidx.paging.ExperimentalPagingApi
import com.example.pagging_remote_medaitor_spacex.data.DefaultLaunchesRepository
import com.example.pagging_remote_medaitor_spacex.domain.LaunchesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)


interface RepositoriesModule {
    @Binds
    fun bindLaunchesRepository(
        launchesRepository: DefaultLaunchesRepository
    ): LaunchesRepository

}
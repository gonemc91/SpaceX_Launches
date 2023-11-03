package com.example.pagging_remote_medaitor_spacex.data

import com.example.pagging_remote_medaitor_spacex.data.retrofit.LaunchesRepository
import com.example.pagging_remote_medaitor_spacex.data.room.LaunchesDao

class DefaultLaunchesRepository(
    private val launchesDao: LaunchesDao,
    private val remoteMediatorFactory: LaunchRemoteMediator

) :LaunchesRepository(){

}

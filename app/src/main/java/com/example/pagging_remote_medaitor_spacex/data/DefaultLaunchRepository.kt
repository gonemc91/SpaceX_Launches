package com.example.pagging_remote_medaitor_spacex.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pagging_remote_medaitor_spacex.data.room.LaunchRoomEntity
import com.example.pagging_remote_medaitor_spacex.data.room.LaunchesDao
import com.example.pagging_remote_medaitor_spacex.domain.Launch
import com.example.pagging_remote_medaitor_spacex.domain.LaunchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Implementation of [LaunchesRepository] which uses
 * [LaunchesDao] and [LaunchesRemoteMediator]
 */
@ExperimentalPagingApi
@Singleton
class DefaultLaunchesRepository @Inject constructor(
    private val launchesDao: LaunchesDao,
    private val remoteMediatorFactory: LaunchesRemoteMediator.Factory,
    ) : LaunchesRepository {

    override fun getLaunches(year: Int?): Flow<PagingData<Launch>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE// not required, may be deleted
            ),
            remoteMediator = remoteMediatorFactory.create(year = year),
            pagingSourceFactory = { launchesDao.getPagingSource(year) }
        )
            .flow
            //.map { it as PagingData<Launch> }
            .map { pagingData ->
                pagingData.map { launchRoomEntity ->
                    launchRoomEntity
                }
            }
    }

    override suspend fun toggleSuccessFlag(launch: Launch) {

        //TODO("call an endpoint here for editing the Launch if such endpoint exists")

        val editedEntity = LaunchRoomEntity(launch)
            .copy(isSuccess = !launch.isSuccess)

        launchesDao.save(editedEntity)
    }

    private companion object{
        const val PAGE_SIZE = 30
    }
}

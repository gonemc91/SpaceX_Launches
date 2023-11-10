package com.example.pagging_remote_medaitor_spacex.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pagging_remote_medaitor_spacex.data.retrofit.LaunchesApi
import com.example.pagging_remote_medaitor_spacex.data.retrofit.LaunchesQuery
import com.example.pagging_remote_medaitor_spacex.data.room.LaunchRoomEntity
import com.example.pagging_remote_medaitor_spacex.data.room.LaunchesDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalPagingApi::class)
@ExperimentalCoroutinesApi
class LaunchesRemoteMediator @AssistedInject constructor(
    private val launchesDao: LaunchesDao,
    private val  launchesApi : LaunchesApi,
    @Assisted private val year: Int?
) : RemoteMediator<Int, LaunchRoomEntity>() {

    private var pageIndex = 0


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LaunchRoomEntity>
    ): MediatorResult {

        pageIndex = getPagesIndex(loadType) ?:
        return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize
        val offset = pageIndex * limit

        return try {
            val launches = fetchLaunches(limit, offset)


            launchesDao.save(launches)

            if (loadType == LoadType.REFRESH){
                launchesDao.refresh(year, launches)
            }else{
                launchesDao.save(launches)
            }
            MediatorResult.Success(
                endOfPaginationReached = launches.size<limit
            )
        }catch (e: Exception){
            MediatorResult.Error(e)
        }
    }


    private fun getPagesIndex(loadType: LoadType): Int?{
        pageIndex = when(loadType){
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }


    //Fetching data from Server to DataBase
    private suspend fun fetchLaunches(
        limit: Int,
        offset: Int
    ): List<LaunchRoomEntity> {
        val query = LaunchesQuery.create(
            year = year,
            limit = limit,
            offset = offset
        )
        return launchesApi.getLaunches(query)
            .docs
            .map {
                Log.d("NetworkLaunches", "id:${it.id}, Name: ${it.name}, detail: ${it.detail}, Unix_data: ${it.launchTimestamp}  ${it.dateUnix},  Year: ${it.year}")
                LaunchRoomEntity(it)
            }
    }

    @AssistedFactory
    interface Factory{
        fun create (year: Int?): LaunchesRemoteMediator
    }

}


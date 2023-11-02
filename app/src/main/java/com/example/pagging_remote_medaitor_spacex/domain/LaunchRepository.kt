package com.example.pagging_remote_medaitor_spacex.domain


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


interface LaunchRepository {

    /**
     * Get pages list of launches for the specified year.
     *If year is NULL, all launches are queried.
     */

  fun getLaunches(year: Int? = null): Flow<PagingData<Launch>>

    /**
     * Toggle the [Launch.isSuccess] flag for the specified launch.
     */


    suspend fun toggleSuccessFlag (launch: Launch)


}
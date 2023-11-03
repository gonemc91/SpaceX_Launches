package com.example.pagging_remote_medaitor_spacex.data.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LaunchesDao {

    /**
     * Note that orderBy and ASC/DESC order should be the same as
     * in the network request.
     */

    @Query("SELECT * FROM launches WHERE :year is NULL OR year = :year ORDER by launchTimestamp DESC")
    fun getPagingSource(
        year: Int
    ): PagingSource<Int, LaunchRoomEntity>

    /**
     *Insert (or replace by ID) list SpaceX Launches.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(launch: List<LaunchRoomEntity>)


    /**
     * Clear local records for the specified year ( or clear all
     * local records in year is NULL
     */
    @Query("DELETE FROM launches WHERE :year IS NULL OR year = :year")
    suspend fun clear(year: Int?)


    /**
     * Clear old records and place records from the list.
     */

    suspend fun refresh(year: Int?, launches: List<LaunchRoomEntity>){
        clear(year)
        save(launches)
    }
    /**
     *Convenient call for saving one Launch entity.
     */
    suspend fun save (launch: LaunchRoomEntity){
        save(listOf(launch))
    }


}
package com.example.pagging_remote_medaitor_spacex.data.retrofit


import retrofit2.http.Body
import retrofit2.http.POST

/**
 * API for fetching SpaceX Launches
 *
 */

interface LaunchesApi {

    /**
     * Note than orderBy and ASC/DESC order should be the same as the database query!
     */
    @POST("launches/query")
    suspend fun getLaunches(
        @Body launchesQuery: LaunchesQuery
    ): LaunchesResponse

}

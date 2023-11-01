package com.example.pagging_remote_medaitor_spacex.domain

/**
 * Interface for unit internet data and database data
 */
interface Launch {
    val id: Long
    val name: String
    val description: String
    val imageUrl: String
    val year: Int
    val launchTimestamp: Long
    val isSuccess: Boolean
}

package com.example.pagging_remote_medaitor_spacex

import java.util.Calendar
import java.util.concurrent.TimeUnit

fun main(){

    val launchTimestamp: Long = 1612419540


    val year = Calendar.getInstance().apply {
        timeInMillis = TimeUnit.SECONDS.toMillis(launchTimestamp)
    }.get(Calendar.YEAR)


    println("Date_unix: $launchTimestamp, year: $year")

}
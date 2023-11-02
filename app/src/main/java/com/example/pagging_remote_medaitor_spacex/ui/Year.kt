package com.example.pagging_remote_medaitor_spacex.ui

import android.content.Context
import com.example.pagging_remote_medaitor_spacex.R


class Year(
    val value: Int?,
    private val message: String
) {

    constructor(context: Context, year: Int?): this(
        value = year,
        message = year.toString() ?: context.getString(R.string.all)
    )

    override fun toString(): String {
        return message
    }

}
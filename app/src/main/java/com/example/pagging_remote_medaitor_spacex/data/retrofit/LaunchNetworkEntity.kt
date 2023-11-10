package com.example.pagging_remote_medaitor_spacex.data.retrofit

import com.example.pagging_remote_medaitor_spacex.domain.Launch
import com.google.gson.annotations.SerializedName
import java.util.Calendar
import java.util.concurrent.TimeUnit

data class LaunchNetworkEntity(
    @SerializedName("flight_number") override val id: Long,
    override val name: String,
    val detail: String?,
    val links: Links?,
    val dateUnix: Long,
    val success: Boolean,
) : Launch {
    override val description: String get() = detail ?: "-"
    override val isSuccess: Boolean get() = success
    override val imageUrl: String get() = links?.patch?.small ?: ""

    override val launchTimestamp: Long get() = dateUnix

    override val year: Int get() = Calendar.getInstance().apply {
        timeInMillis = TimeUnit.SECONDS.toMillis(launchTimestamp)
    }.get(Calendar.YEAR)
}

data class Links(
    val patch: Images?
)

data class Images(
    val small: String?
)

package com.example.pagging_remote_medaitor_spacex.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


//TODO Need repeat lesson about Live Data
class Event<T>(
    value: T
) {

    private var _value: T? = value

    fun get() : T? = _value.also { _value=null }

}
//--helper method / aliases

/**
 * Convert mutable live-data into non-mutable live-data.
 * Extension function.
 */

fun <T> MutableLiveData<T>.share(): LiveData<T> = this



//type aliases for live-data instance which contain events
typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>
typealias LiveEvent<T> = LiveData<Event<T>>
typealias EventListener<T> = (T)-> Unit

fun <T> MutableLiveEvent<T>.publishEvent(value: T){
    this.value = Event(value)
}

fun <T> LiveEvent<T>.observeEvent(lifecycleOwner: LifecycleOwner, listener: EventListener<T>) {
    this.observe(
        lifecycleOwner
    ) {
        it.get()?.let { value ->
            listener(value)
        }
    }
}
package com.example.pagging_remote_medaitor_spacex.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.pagging_remote_medaitor_spacex.domain.Launch

class LaunchesItemCallback : DiffUtil.ItemCallback<LaunchUiEntity>() {
    override fun areItemsTheSame(oldItem: LaunchUiEntity, newItem: LaunchUiEntity): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LaunchUiEntity, newItem: LaunchUiEntity): Boolean {
        return oldItem == newItem
    }
}
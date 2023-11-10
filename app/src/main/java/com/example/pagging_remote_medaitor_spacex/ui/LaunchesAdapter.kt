package com.example.pagging_remote_medaitor_spacex.ui
import android.content.res.ColorStateList
import android.graphics.Color
import com.bumptech.glide.Glide
import com.elveum.elementadapter.context
import com.elveum.elementadapter.getColor
import com.elveum.elementadapter.setTintColor
import com.example.pagging_remote_medaitor_spacex.R
import com.example.pagging_remote_medaitor_spacex.databinding.ItemLaunchBinding
import com.example.pagging_remote_medaitor_spacex.ui.base.pagingAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
fun launchesAdapter(viewModel: MainViewModel) = pagingAdapter<LaunchUiEntity, ItemLaunchBinding> {
    areItemsSame = {oldItem, newItem -> oldItem.id == newItem.id  }
    bind {launch->
        nameTextView.text = launch.name
        descriptionTextView.text = launch.description
        selectCheckBox.isChecked = launch.isChecked
        root.setBackgroundColor(
            if(launch.isChecked)
                getColor(R.color.checked_background)
            else
                Color.TRANSPARENT
        )

        if(launch.imageUrl.isNotBlank()){
            photoImageView.imageTintList = null
            Glide.with(context())
                .load(launch.imageUrl)
                .centerCrop()
                .into(photoImageView)
        }else{
            photoImageView.imageTintList = ColorStateList.valueOf(Color.GRAY)
            Glide.with(context())
                .load(R.drawable.ic_no_image)
                .into(photoImageView)
        }

        yearValueTextView.text = launch.year.toString()

        if(launch.isSuccess){
           statusImageView.setImageResource(R.drawable.ic_success)
            statusImageView.setTintColor(R.color.success)
        }else{
            statusImageView.setImageResource(R.drawable.ic_fail)
            statusImageView.setTintColor(R.color.fail)
        }

        listeners {
            checkContainer.onClick { viewModel.toggleCheckState(it) }
            statusImageView.onClick { viewModel.toggleSuccessFlag(it) }

        }




    }


}
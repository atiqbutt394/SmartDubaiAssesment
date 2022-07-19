package com.example.nytimes.ui.activity.bindngadapter

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object CustomViewBinding {

    //extension function to handle visibility of progressbar...
    @BindingAdapter(value = ["setupVisibility"])
    @JvmStatic
    fun ProgressBar.progressVisibility(loadingState: LoadingState?) {
        loadingState?.let {
            isVisible = when (it.status) {
                LoadingState.Status.RUNNING -> true
                LoadingState.Status.SUCCESS -> false
                LoadingState.Status.FAILED -> false
            }
        }
    }

    //extension function for setting imageURL...
    @BindingAdapter(value = ["setImageUrl"])
    @JvmStatic
    fun ImageView.bindImageUrl(url: String?) {
        /*if (url != null && url.isNotBlank()) {*/
        // using static url link, as no image url parameter is available...
        Glide.with(context)
            .load("https://www.dcsltd.co.uk/wp-content/themes/dcs/images/ui.news-image-placeholder.jpg")
            .circleCrop()
            .into(this);
        /*  }*/
    }

    //extension function for binding adapter in fragment...
    @BindingAdapter(value = ["setAdapter"])
    @JvmStatic
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.run {
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }
}
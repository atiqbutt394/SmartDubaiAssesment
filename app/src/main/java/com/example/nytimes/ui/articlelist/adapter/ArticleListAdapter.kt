package com.example.nytimes.articlelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimes.data.responses.Results
import com.example.nytimes.databinding.ArticleListItemBinding

class ArticleListAdapter(val clickListener: ArticleListClickListener):
    ListAdapter<Results, ArticleListAdapter.ArticleListViewHolder>(Companion) {

    inner class ArticleListViewHolder(val binding: ArticleListItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ArticleListItemBinding.inflate(layoutInflater, parent, false)
        return ArticleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.binding.articleItem = currentUser
        holder.binding.clickListener = clickListener
        holder.binding.executePendingBindings()
    }

     class ArticleListClickListener(val clickListener: (mResult: Results) -> Unit){
        fun onClick(result : Results) = clickListener(result)
    }
}
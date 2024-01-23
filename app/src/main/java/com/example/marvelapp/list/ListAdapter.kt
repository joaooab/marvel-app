package com.example.marvelapp.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.model.Comic
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ItemComicBinding

class ListAdapter : PagingDataAdapter<Comic, ListAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemComicBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(
        private val binding: ItemComicBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic) {
            binding.textName.text = comic.title
            Glide.with(itemView)
                .load(comic.thumbnail)
                .fallback(R.drawable.ic_image_error)
                .into(binding.image)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Comic>() {
            override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                return oldItem == newItem
            }
        }
    }
}
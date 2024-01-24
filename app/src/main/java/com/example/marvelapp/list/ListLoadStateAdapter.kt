package com.example.marvelapp.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ItemLoadMoreStateBinding

class ListLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ListLoadStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemLoadMoreStateBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

    inner class ViewHolder(
        private val binding: ItemLoadMoreStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) = with(binding) {
            progressBarLoadingMore.isVisible = loadState is LoadState.Loading
            textTryAgain.isVisible = loadState is LoadState.Error
            textTryAgain.setOnClickListener { retry() }
        }
    }
}
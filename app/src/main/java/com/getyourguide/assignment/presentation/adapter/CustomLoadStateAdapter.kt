package com.getyourguide.assignment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.getyourguide.assignment.databinding.ItemLoadStateBinding

class CustomLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CustomLoadStateAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: CustomLoadStateAdapter.ViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        ViewHolder(ItemLoadStateBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), retry)

    inner class ViewHolder(
        binding: ItemLoadStateBinding,
        retry: () -> Unit,
    ) : RecyclerView.ViewHolder(
        binding.root) {
        private val progressBar: ProgressBar = binding.progressBar
        private val retry: Button = binding.buttonRetry
            .also {
                it.setOnClickListener { retry() }
            }

        fun bind(loadState: LoadState) {
            // if (loadState is LoadState.Error) error handling

            progressBar.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState is LoadState.Error

        }
    }
}
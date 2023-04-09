package com.reyansh.pagination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.R

class LoaderStateAdapter() :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_loader, parent, false)
        return LoaderViewHolder(view)
    }

    /**
     * view holder class for footer loader and error state handling
     */
    class LoaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val pbLoader: ProgressBar = view.findViewById(R.id.pbLoader)
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Loading) {
                pbLoader.visibility = View.VISIBLE
            } else {
                pbLoader.visibility = View.GONE
            }
        }
    }
}

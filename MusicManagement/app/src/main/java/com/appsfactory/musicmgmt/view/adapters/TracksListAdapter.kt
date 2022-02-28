package com.appsfactory.musicmgmt.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.Track
import com.appsfactory.musicmgmt.databinding.ItemTrackLayoutBinding

class TracksListAdapter :
    ListAdapter<Track, TracksListAdapter.TrackViewModel>(trackListDiffCallback) {
    inner class TrackViewModel(itemBinding: ItemTrackLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        val txtTrackName = itemBinding.txtTrackName
        val txtListeners = itemBinding.txtDuration

        fun bind(track: Track) {
            txtTrackName.text = track.name

            val min = track.duration?.div(60)
            val sec = track.duration?.rem(60)
            txtListeners.text = "$min min $sec sec"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TracksListAdapter.TrackViewModel {
        val itemBinding: ItemTrackLayoutBinding = ItemTrackLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TrackViewModel(itemBinding)
    }

    override fun onBindViewHolder(holder: TracksListAdapter.TrackViewModel, position: Int) {
        holder.bind(getItem(position))
    }
}

private val trackListDiffCallback = object : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}
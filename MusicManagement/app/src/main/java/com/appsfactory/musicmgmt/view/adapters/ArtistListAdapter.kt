package com.appsfactory.musicmgmt.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.appsfactory.musicmgmt.R
import com.appsfactory.musicmgmt.data.remote.network.models.artistSearchModels.Artist
import com.appsfactory.musicmgmt.databinding.ItemArtistLayoutBinding


class ArtistListAdapter :
    ListAdapter<Artist, ArtistListAdapter.ArtistViewModel>(artistListDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewModel {
        context = parent.context
        val itemBinding: ItemArtistLayoutBinding = ItemArtistLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ArtistViewModel(itemBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewModel, position: Int) {

        val artist = getItem(position)
        holder.txtArtistName.text = artist.name

        if (artist.image[0].text?.isNotEmpty() == true) {
            holder.imgArtist.load(artist.image[0].text) {
                size(50, 50)
            }
        }
        Log.d("album url======>", artist.image[0].text.toString())
    }

    private lateinit var context: Context
    var onItemClick: ((Artist) -> Unit)? = null

    inner class ArtistViewModel(itemBinding: ItemArtistLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val txtArtistName = itemBinding.txtArtistName
        val imgArtist = itemBinding.imgArtist

        init {
            itemBinding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }
}

private val artistListDiffCallback = object : DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.mBid == newItem.mBid
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem == newItem
    }
}
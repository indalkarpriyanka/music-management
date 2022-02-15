package com.appsfactory.musicmgmt.view.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.appsfactory.musicmgmt.R
import com.appsfactory.musicmgmt.data.remote.network.models.topAlbumsModels.Album
import com.appsfactory.musicmgmt.databinding.ItemAlbumLayoutBinding

class TopAlbumAdapter :
    ListAdapter<Album, TopAlbumAdapter.TopAlbumViewHolder>(topAlbumListDiffCallback) {
    var onItemClick: ((Album) -> Unit)? = null
    private lateinit var context: Context

    inner class TopAlbumViewHolder(itemBinding: ItemAlbumLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val imgAlbum = itemBinding.imgAlbum
        val txtAlbumName = itemBinding.txtAlbumName

        init {
            itemBinding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumViewHolder {
        context = parent.context
        val itemBinding: ItemAlbumLayoutBinding =
            ItemAlbumLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopAlbumViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TopAlbumViewHolder, position: Int) {
        var album = getItem(position)

        holder.txtAlbumName.text = if (album.name.isNullOrEmpty()) {
            context.getString(R.string.no_title)
        } else {
            album.name
        }

        if (album.image[1].text?.isNotEmpty() == true) {
            holder.imgAlbum.load(album.image[1].text){
                crossfade(true)
            }
        }
    }
}

private val topAlbumListDiffCallback = object : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.mbid == newItem.mbid
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}
package com.appsfactory.musicmgmt.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import coil.load
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.common.utils.Constants
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.AlbumDetailResponseModel
import com.appsfactory.musicmgmt.databinding.FragmentAlbumDetailsBinding
import com.appsfactory.musicmgmt.presentation.MainActivity
import com.appsfactory.musicmgmt.presentation.viewModels.AlbumDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumDetailsFragment : Fragment() {

    private lateinit var viewModel: AlbumDetailsViewModel
    private lateinit var albumResponseModel: AlbumDetailResponseModel
    private var binding: FragmentAlbumDetailsBinding? = null

    private fun callGetAlbumDetailsApi(albumId: String?, albumName: String, artistName: String) {

        viewModel.getAlbumDetails(albumId, artistName, albumName)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: AlbumDetailsFragmentArgs by navArgs()

        viewModel = (requireActivity() as MainActivity).compositeRoot.albumDetailsViewModel
        callGetAlbumDetailsApi(args.mbid, args.artistName, args.albumName)
        setData()
    }

    private fun setData() {
        viewModel.albumDetailResponseModel.observe(viewLifecycleOwner) {
            when (it) {
                is ResultModel.Success -> {
                    binding?.pgBar?.visibility =
                        View.GONE
                    albumResponseModel = it.data!!
                    Log.d("url====>", Constants.formatUrl(it.data.album.image[0].toString()))
                    binding?.imgAlbum?.load(it.data.album.image[1].toString())
                    binding?.txtArtistNameValue?.text = it.data.album.artist
                    (activity as AppCompatActivity?)!!.supportActionBar!!.title =
                        it.data.album.name
                }
                is ResultModel.Error -> {
                    binding?.pgBar?.visibility =
                        View.GONE
                }
                is ResultModel.Loading -> {
                    binding?.pgBar?.visibility =
                        View.VISIBLE

                }
            }

        }

        binding?.btnFavourite?.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
               // viewModel.addAlbumToDatabase(albumResponseModel.album.toAlbumEntity())
            }
        }
    }


}
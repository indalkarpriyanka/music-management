package com.appsfactory.musicmgmt.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.appsfactory.musicmgmt.R
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.common.utils.Constants
import com.appsfactory.musicmgmt.common.utils.Constants.MESSAGE
import com.appsfactory.musicmgmt.data.local.dao.AlbumEntity
import com.appsfactory.musicmgmt.databinding.FragmentAlbumDetailsBinding
import com.appsfactory.musicmgmt.presentation.MainActivity
import com.appsfactory.musicmgmt.presentation.uiModels.AlbumUiModel
import com.appsfactory.musicmgmt.presentation.viewModels.AlbumDetailsViewModel
import com.appsfactory.musicmgmt.view.adapters.TracksListAdapter


class AlbumDetailsFragment : Fragment() {

    private lateinit var viewModel: AlbumDetailsViewModel
    private lateinit var albumModel: AlbumEntity
    private lateinit var albumUiModel: AlbumUiModel
    private lateinit var tracksListAdapter: TracksListAdapter
    private var binding: FragmentAlbumDetailsBinding? = null

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
        albumUiModel = args.albumModel
        viewModel = (requireActivity() as MainActivity).compositeRoot.albumDetailsViewModel
        getAlbumDetails()
        setData()
        setAdapter()
    }

    private fun setAdapter() {
        tracksListAdapter = TracksListAdapter()
        binding?.rvTracks?.adapter = tracksListAdapter
    }

    private fun setButtonStatusDownload() {
        binding?.btnFavourite?.setText(Constants.DOWNLOAD)
        binding?.btnFavourite?.setOnClickListener {
            viewModel.addAlbumToDatabase(albumModel)
            setButtonStatusRemove()
        }
    }

    private fun setButtonStatusRemove() {
        binding?.btnFavourite?.setText(Constants.REMOVE)
        binding?.btnFavourite?.setOnClickListener {
            viewModel.removeAlbum(albumModel.id)
            albumModel.id = 0
            setButtonStatusDownload()
        }
    }

    private fun getAlbumDetails() {
        if (albumUiModel.id != 0) {
            getAlbumDetailsFromDb(albumUiModel.id)
            setButtonStatusRemove()
        } else {
            callGetAlbumDetailsApi(albumUiModel)
        }
    }

    private fun getAlbumDetailsFromDb(id: Int) {
        viewModel.getAlbumDetailsFRomDb(id)
    }

    private fun callGetAlbumDetailsApi(album: AlbumUiModel) {
        if (Constants.isInternetAvailable(requireActivity())) {
            viewModel.getAlbumDetails(album)
        } else {
            Constants.showInternetErrorDialog(requireActivity().supportFragmentManager)
        }
    }

    private fun setData() {

        viewModel.albumDetailResponseModel.observe(
            viewLifecycleOwner
        ) { it ->
            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                when (it) {
                    is ResultModel.Success -> {
                        binding?.pgBar?.visibility =
                            View.GONE

                        if (it.data != null) {
                            albumModel = it.data
                            displayData()
                        }
                    }
                    is ResultModel.Error -> {
                        binding?.pgBar?.visibility =
                            View.GONE
                        showDialog(it.message)
                    }
                    is ResultModel.Loading -> {
                        binding?.pgBar?.visibility =
                            View.VISIBLE
                    }
                }
            }
        }
    }

    private fun showDialog(message: String?) {
        var dialogFragment = DialogFragment()
        val args = Bundle()
        args.putString(MESSAGE, message)
        dialogFragment.setArguments(args)
        dialogFragment.dismissClicked = { findNavController().popBackStack() }
        dialogFragment.show(requireActivity().supportFragmentManager, "Error")

    }

    private fun displayData() {
        if (!albumModel.image.isNullOrEmpty()) {
            binding?.imgAlbum?.load(albumModel.image) {
                crossfade(true)
                placeholder(R.drawable.img_album_placeholder)
            }
        }

        if (!albumModel.tracks.isNullOrEmpty()) {
            binding?.txtTracks?.visibility = View.VISIBLE
            tracksListAdapter.submitList(albumModel.tracks)
        } else {
            binding?.txtTracks?.visibility = View.GONE
        }
        binding?.txtAlbumNameValue?.text = albumModel.name
        binding?.txtArtistNameValue?.text = albumModel.artistName
        (activity as AppCompatActivity?)!!.supportActionBar!!.title =
            albumModel.name

        if (albumModel.id != 0) {
            setButtonStatusRemove()
        } else {
            setButtonStatusDownload()
        }
    }
}
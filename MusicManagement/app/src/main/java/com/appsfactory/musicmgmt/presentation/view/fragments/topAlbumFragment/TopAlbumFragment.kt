package com.appsfactory.musicmgmt.presentation.view.fragments.topAlbumFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.databinding.FragmentTopAlbumBinding
import com.appsfactory.musicmgmt.common.utils.Constants
import com.appsfactory.musicmgmt.presentation.MainActivity
import com.appsfactory.musicmgmt.presentation.view.adapters.TopAlbumAdapter

class TopAlbumFragment : Fragment() {

    private lateinit var artistName: String
    private lateinit var topAlbumsViewModel: TopAlbumsViewModel
    private lateinit var topAlbumAdapter: TopAlbumAdapter
    private var toAlbumFragmentBinding: FragmentTopAlbumBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            artistName = it.getString(Constants.ARTIST_NAME)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toAlbumFragmentBinding = FragmentTopAlbumBinding.inflate(inflater, container, false)
        return toAlbumFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        topAlbumsViewModel =
            (requireActivity() as MainActivity).compositeRoot.topAlbumsViewModel
        if (Constants.isInternetAvailable(requireActivity())) {
            topAlbumsViewModel.getTopAlbumList(artistName)
        } else {
            Constants.showInternetErrorDialog(requireActivity().supportFragmentManager)
        }

        topAlbumAdapter = TopAlbumAdapter()
        toAlbumFragmentBinding?.rcvAlbumList?.adapter = topAlbumAdapter
        topAlbumsViewModel.searchArtistList.observe(viewLifecycleOwner) { resultModel ->

            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                when (resultModel) {
                    is ResultModel.Loading -> toAlbumFragmentBinding?.pgBar?.visibility =
                        View.VISIBLE
                    is ResultModel.Error -> {
                        toAlbumFragmentBinding?.pgBar?.visibility = View.GONE
                        Log.d("Error", resultModel.message.toString())
                    }
                    is ResultModel.Success -> {
                        toAlbumFragmentBinding?.pgBar?.visibility = View.GONE
                        topAlbumAdapter.submitList(resultModel.data)
                    }
                }
            }
        }

        topAlbumAdapter.onItemClick = { album ->
            val bundle = Bundle()
            bundle.putString(Constants.ALBUM_MID, album.mbid)
            findNavController().navigate(
                TopAlbumFragmentDirections.actionTopAlbumFragmentToAlbumDetailsFragment(
                    album
                )
            )
        }
    }
}
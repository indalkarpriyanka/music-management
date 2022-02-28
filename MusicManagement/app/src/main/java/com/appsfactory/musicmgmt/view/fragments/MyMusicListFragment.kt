package com.appsfactory.musicmgmt.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.common.utils.Constants
import com.appsfactory.musicmgmt.databinding.FragmentMyMusicListBinding
import com.appsfactory.musicmgmt.presentation.MainActivity
import com.appsfactory.musicmgmt.presentation.viewModels.MyAlbumListViewModel
import com.appsfactory.musicmgmt.view.adapters.TopAlbumAdapter


class MyMusicListFragment : Fragment() {

    private var binding: FragmentMyMusicListBinding? = null
    private lateinit var viewModel: MyAlbumListViewModel
    private lateinit var myAlbumListAdapter: TopAlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as MainActivity).compositeRoot.myAlbumsListViewModel
        viewModel.getMyAlbumList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyMusicListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setObserver()
    }

    private fun setAdapter() {
        myAlbumListAdapter = TopAlbumAdapter()
        binding?.rvMyAlbumlist?.adapter = myAlbumListAdapter
        myAlbumListAdapter.onItemClick = { album ->
            val bundle = Bundle()
            bundle.putString(Constants.ALBUM_MID, album.mbid)
            findNavController().navigate(
                MyMusicListFragmentDirections.actionMyListFragmentToAlbumDetailsFragment(
                    album
                )
            )
        }
    }

    private fun setObserver() {
        viewModel.myAlbumList.observe(viewLifecycleOwner) { resultModel ->
            when {
                resultModel is ResultModel.Success -> {
                    if (resultModel.data?.isNotEmpty() == true) {
                        binding?.tvNoData?.visibility = View.GONE
                        myAlbumListAdapter.submitList(resultModel.data)
                    } else {
                        binding?.tvNoData?.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
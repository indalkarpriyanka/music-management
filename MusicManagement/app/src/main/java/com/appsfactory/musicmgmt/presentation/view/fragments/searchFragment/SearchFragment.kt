package com.appsfactory.musicmgmt.presentation.view.fragments.searchFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.appsfactory.musicmgmt.R
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.databinding.FragmentSearchBinding

import com.appsfactory.musicmgmt.common.utils.Constants
import com.appsfactory.musicmgmt.common.utils.Constants.isInternetAvailable
import com.appsfactory.musicmgmt.common.utils.Constants.showInternetErrorDialog
import com.appsfactory.musicmgmt.presentation.MainActivity
import com.appsfactory.musicmgmt.presentation.view.adapters.ArtistListAdapter
import com.google.android.material.snackbar.Snackbar


class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel
    private lateinit var artistAdapter: ArtistListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as MainActivity).compositeRoot.searchViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel.searchArtistList.observe(viewLifecycleOwner) { resultModel ->


            when (resultModel) {
                is ResultModel.Loading -> binding?.pgBar?.visibility = View.VISIBLE
                is ResultModel.Error -> {
                    binding?.pgBar?.visibility = View.GONE
                    Log.d("Error", resultModel.message.toString())
                }
                is ResultModel.Success -> {
                    binding?.pgBar?.visibility = View.GONE
                    if (resultModel.data?.results?.artistMatches?.artist?.isNotEmpty() == true) {
                        binding?.tvNoData?.visibility = View.GONE
                        artistAdapter.submitList(resultModel.data.results.artistMatches.artist)
                    } else {
                        binding?.tvNoData?.visibility = View.VISIBLE
                    }
                }
            }

        }
        artistAdapter = ArtistListAdapter()
        binding?.rcvArtistList?.adapter = artistAdapter
        artistAdapter.onItemClick = { artist ->
            artist.name?.let { createTopAlbumsFragment(it) }
        }
        binding?.btnSearch?.setOnClickListener {
            it.hideKeyboard()
            if (binding?.etSearchText?.text.isNullOrEmpty()) {
                Snackbar.make(requireView(), "Please enter valid text", 3000).show()
            } else {
                callGetArtistListApi(binding?.etSearchText?.text.toString())
            }
        }
    }

    private fun createTopAlbumsFragment(artistName: String) {
        val bundle = Bundle()
        bundle.putString(Constants.ARTIST_NAME, artistName)
        findNavController().navigate(R.id.action_searchFragment_to_topAlbumFragment, bundle)
    }

    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun callGetArtistListApi(searchText: String) {
        if (isInternetAvailable(requireActivity())) {
            viewModel.getArtistList(searchText)
        } else {
            showInternetErrorDialog(requireActivity().supportFragmentManager)
        }
    }
}
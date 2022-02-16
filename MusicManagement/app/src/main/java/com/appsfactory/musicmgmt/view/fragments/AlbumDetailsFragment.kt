package com.appsfactory.musicmgmt.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.appsfactory.musicmgmt.common.ResultModel
import com.appsfactory.musicmgmt.data.remote.network.models.albumDetailsModels.AlbumDetailResponseModel
import com.appsfactory.musicmgmt.databinding.FragmentAlbumDetailsBinding
import com.appsfactory.musicmgmt.presentation.MainActivity
import com.appsfactory.musicmgmt.presentation.viewModels.AlbumDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AlbumDetailsFragment : Fragment() {

    var thiscontext: Context? = null

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
        thiscontext = container?.context
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

                    it.data.album.let { album ->
                        Log.d("url====>", album.image[1].text.toString())
                        binding?.imgAlbum?.load(album.image[1].text)
                        binding?.txtAlbumNameValue?.text = album.name
                        binding?.txtArtistNameValue?.text = album.artist
                        /*(activity as AppCompatActivity?)!!.supportActionBar!!.title =
                            album.name*/


                        val N = album.tracks.track.size // total number of textviews to add
                        val myTextViews = arrayOfNulls<TextView>(N) // create an empty array;
                        for (i in 0 until N) {
                            // create a new textview
                            val rowTextView = TextView(thiscontext)

                            // set some properties of rowTextView or something
                            rowTextView.text = album.tracks.track[i].name

                            // add the textview to the linearlayout
                            binding?.llTrackNames?.addView(rowTextView)

                            // save a reference to the textview for later
                            myTextViews[i] = rowTextView
                        }


                    }
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
package com.appsfactory.musicmgmt.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appsfactory.musicmgmt.R
import com.appsfactory.musicmgmt.common.utils.Constants.MESSAGE
import com.appsfactory.musicmgmt.databinding.FragmentDialogBinding
import com.appsfactory.musicmgmt.databinding.FragmentSearchBinding


class DialogFragment : androidx.fragment.app.DialogFragment() {

    private var message: String? = null
    private var binding: FragmentDialogBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString(MESSAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        dialog?.requestWindowFeature(STYLE_NO_TITLE)
        binding?.tvMessage?.text = message
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnOk?.setOnClickListener { dialog?.dismiss() }
    }
}
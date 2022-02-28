package com.appsfactory.musicmgmt.presentation.view.fragments.dialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appsfactory.musicmgmt.common.utils.Constants.MESSAGE
import com.appsfactory.musicmgmt.databinding.FragmentDialogBinding


class DialogFragment : androidx.fragment.app.DialogFragment() {

    private var message: String? = null
    var dismissClicked: (() -> Unit)? = null
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
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        binding?.tvMessage?.text = message
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnOk?.setOnClickListener { dialog?.dismiss()
            dismissClicked?.invoke()
        }
    }
}